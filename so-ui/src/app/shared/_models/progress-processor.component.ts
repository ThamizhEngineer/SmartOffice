import { Component, OnInit, ViewChild, TemplateRef, Input, Output, EventEmitter, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ProgressProcessorService } from '../_services/progress-processor.service';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/takeWhile';
import 'rxjs/add/observable/timer'

var knownBkProcessActions = ["gap-analysis", "process-payroll", "employee-assessement"]
var buttonConfigs = [
    { id: "default", buttonColor: "btn btn-danger", buttonIcon: "fa fa-times", buttonMessage: "Not ready" },
    { id: "loading", buttonColor: "btn btn-light", buttonIcon: "fa fa-spinner", buttonMessage: "Processing" },
    { id: "error", buttonColor: "btn btn-warning", buttonIcon: "fa fa-exclamation-triangle", buttonMessage: "Error" },
    { id: "ready", buttonColor: "btn btn-success", buttonIcon: "fa fa-check", buttonMessage: "Ready" },
    { id: "info", buttonColor: "btn btn-info", buttonIcon: "fa fa-info", buttonMessage: "Check progress" },
    { id: "completed", buttonColor: "btn btn-dark", buttonIcon: "fa fa-thumbs-o-up", buttonMessage: "Completed" },
    { id: "empty", buttonColor: "btn btn-warning", buttonIcon: "fa fa-exclamation-triangle", buttonMessage: "No data" }

];

@Component({
    selector: 'progress-processor',
    templateUrl: 'progress-processor.component.html',
    styleUrls: ['progress-processor.component.scss'],
    providers: [ProgressProcessorService]
})

export class ProgressProcessorComponent implements OnInit {
    @ViewChild('progressModel') progressModel: TemplateRef<any>;
    modalReference: any = null;

    // Config for progress bar
    isAnimated: boolean = true;
    isStripped: boolean = true;
    value = 0;
    progressBarType = "primary";
    currentProgress = "0%";

    // Button configs
    currentButtonIcon = "";
    currentButtonStatus = "";
    currentButtonColor = "";
    currentButtonName = "Progress";

    // Others
    currentHeaderName = "Employee Payslip Progress"
    bkProcess;

    currentCommand = "";
    currentPayLoad;

    alive = true;

    // Receiving end
    @Input() config: any;
    @Input() payload: any;
    @Input() command: any;

    @Output() responseEvent = new EventEmitter<string>();

    constructor(private modalService: NgbModal, private _Service: ProgressProcessorService) { }

    ngOnInit() {
        this._starter();
    }

    _starter() {
        this.currentButtonName = this.config.buttonName;
        this.setButtonConfigs("default");
    }

    ngOnChanges(changes: SimpleChanges) {
        for (let property in changes) {
            if (property === 'command') {
                // console.log("command change", changes[property].currentValue)
                this.assignProcessUsingCommand(changes[property].currentValue);
                this.currentCommand = changes[property].currentValue;
            }
            if (property === 'payload') {
                // console.log("payload change", changes[property].currentValue)
                this.afterPayloadChange(changes[property].currentValue, changes[property].firstChange);
                this.currentPayLoad = changes[property].currentValue;
            }
        }
    }

    assignProcessUsingCommand(currentCommand) {
        if (currentCommand != undefined && currentCommand == "init") {
            this.setButtonConfigs("ready");
        }
        if (currentCommand != undefined && currentCommand == "check-progress") {
            this.setButtonConfigs("info");
        }
    }

    afterPayloadChange(currentChange, firstChange) {
        if (this.currentCommand != "" && this.currentCommand == "init") {
            if (firstChange == false && currentChange != undefined) {
                // console.log("Current Value - ", currentChange);
                if (knownBkProcessActions.find(x => x === currentChange.processType)) {
                    // console.log("valid process type");
                    this.saveBkProcess(currentChange.processType, currentChange.payloadBody);
                } else {
                    console.log("Invalid process type")
                }
            }
        } else if (this.currentCommand != "" && this.currentCommand == "check-progress") {
            console.log(currentChange)
            if (currentChange.processId!=null || currentChange.processId!=undefined) {
                this.alive = true;
                this.checkStatus(currentChange.processId);
            }else{
                this.setButtonConfigs("empty");
            }
        }
    }

    saveBkProcess(bkProcessType, bkProcessBody) {
        this._Service.postBkProcess(bkProcessBody, bkProcessType)
            .subscribe(arg => {
                console.log(arg)
                this.bkProcess = arg;
                this.sendResponseBack(this.bkProcess);
                this.checkStatus(this.bkProcess.id);
            }, error => {
                console.log(error)
            });
    }

    subscription;
    checkStatus(id) {
        // console.log("Fire check status", id)
        this.subscription = Observable.timer(0, 10000)
            .takeWhile(() => this.alive) // only fires when component is alive
            .subscribe(() => {
                this._Service.checkStatus(id).subscribe(result => {
                    console.log(result)
                    this.bkProcess = result;
                    if (this.bkProcess.isExpired == "N") {
                        if (this.bkProcess.processStatus == "init") {
                            this.value = 10;
                            this.currentProgress = "10%";
                            this.setButtonConfigs("loading");
                        } else if (this.bkProcess.processStatus == "in-progress") {
                            this.value = 50;
                            this.currentProgress = "50%";
                            this.setButtonConfigs("loading");
                        } else if (this.bkProcess.processStatus == "completed") {
                            this.value = 100;
                            this.currentProgress = "100%";
                            this.setButtonConfigs("completed");
                            this.ngOnDestroy();
                        }
                    } else if (this.bkProcess.isExpired == "Y") {
                        this.setButtonConfigs("error");
                        this.currentProgress = "Taking too long"
                        this.ngOnDestroy();
                    }
                }, (error) => {
                    console.log(error)
                })
            });
    }

    sendResponseBack(res) {
        this.responseEvent.emit(res);
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
        this.alive = false; // switches your IntervalObservable off
    }

    // Helpers ------------------------------------------------

    setButtonConfigs(buttonConfigId) {
        var res = buttonConfigs.filter(x => x.id === buttonConfigId);
        if (res) {
            this.currentButtonIcon = res[0].buttonIcon;
            this.currentButtonStatus = res[0].buttonMessage;
            this.currentButtonColor = res[0].buttonColor;
        }
    }

    // Related to model -------------------------------------

    openModel() {
        this.modalReference = this.modalService.open(this.progressModel, { size: 'lg', backdrop: false, keyboard: false });
    }

    close() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
        this.modalReference.close();
        this.modalReference = null;
        this.alive = false;
    }

}