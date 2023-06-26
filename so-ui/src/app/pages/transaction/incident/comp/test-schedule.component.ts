import { Item } from './../../item-master/vo/item';
import { Component, OnInit, Directive, Input, Output, EventEmitter, ViewChildren, QueryList } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident, IncidentApplicant, IncidentApplicantPdf } from '../../../vo/incident';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { TestReportComponent, NgbdSortableHeader, compare, SortEvent } from './test-report.component';
import { status_css } from '../../../vo/status'
import { elementAt } from 'rxjs/operator/elementAt';


@Component({
    selector: '',
    templateUrl: 'test-schedule.component.html'
})

export class TestScheduleComponent implements OnInit {

    @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;
    sort: any = []
    completed: Array<IncidentApplicant>;
    binding: string = 'true';
    IncidantApplicants: Array<IncidentApplicant>;
    incidantApplicantsFiltered: any = [];
    incident: Incident;
    masterSelected:boolean = false;
    toggleBool: boolean=true;

    searchString: string;
    advSearch: boolean = false;
    institute: any;
    course: any;
    passoutYear: any;
    marks: any;
    historyOfArrears: any;
    isEligibleForTest: any;
    template: any = [];
    tesSearch: boolean = false;
    isStillPendingApproval: boolean = false;
    view: string;
    incidentApplicantPdf: IncidentApplicantPdf;
    statuses: any = status_css;
    page: number = 1;
    msg: any;
    errorMsg: any;
    Alertmsg: any;
    isTestCreated: boolean = true;
    pgNumber: number;
    pageSize: number = 10
    settings = {
        bigBanner: true,
        timePicker: true,
        format: 'yyyy/MM/dd hh:mm a',
        defaultOpen: false
    }
    constructor(
        private activedRouter: ActivatedRoute,
        private service: IncidentService,
        private route: Router
    ) { }

    ngOnInit() {

        this.incident = new Incident();
        this.IncidantApplicants = new Array<IncidentApplicant>();
        this.incidantApplicantsFiltered = new Array<IncidentApplicant>();
        this.completed = new Array<IncidentApplicant>();
        this.incidentApplicantPdf = new IncidentApplicantPdf();

        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.subscribe(params => {
                this.pgNumber = params.page;
                this.service.getIncidentById(params.id).subscribe(x => {
                    this.view = params.view;
                    this.incident = x;

                    this.incident.incidentTestTemplates.forEach(test => {
                        if (test.creationStatus != 'Success') {
                            this.isTestCreated = false;
                        }
                    })

                    if (this.incident.hasTest == 'Y') {
                        this.isStillPendingApproval = true;
                    }
                    this.incident.incidentApplicants.forEach(element => {
                        element.isReallocate = null;
                        element.isApplicantDetails = false, element.isviewApplicantDetails = true;

                        if (element.isEligibleForTest == 'Y' && element.isTestScheduled == 'N' || element.isTestScheduled == null) {
                            element.isTestScheduled = null;

                            this.incidantApplicantsFiltered.push(element);
                            this.testTemplate();
                        } else if (element.isEligibleForTest == 'Y') {
                            this.completed.push(element);
                        }
                        this.sort = this.incident.incidentApplicants;
                    });

                });
            })

        }

    }

    advanceSearch() {
        if (this.advSearch == false) {
            this.advSearch = true;
        } else if (this.advSearch == true) {
            this.advSearch = false;
        }
    }

    reset() {
        this.institute = null; this.course = null, this.passoutYear = null, this.marks = null, this.historyOfArrears = null, this.isEligibleForTest = null
        this.search();
    }

    search() {
        this.service.advanceSearch(this.incident.id, this.institute, this.course, this.passoutYear, this.marks, this.historyOfArrears, this.isEligibleForTest).subscribe(x => {
            this.incidantApplicantsFiltered = [];
            x.forEach(element => {
                if (element.isEligibleForTest == 'N') {
                    element.isEligibleForTest = null;
                    this.incidantApplicantsFiltered.push(element);
                } else {
                    this.incidantApplicantsFiltered.push(element);
                }
            });
        })
    }

    testSearch() {
        if (this.tesSearch == false) {
            this.tesSearch = true;
        } else if (this.tesSearch == true) {
            this.tesSearch = false;
        }
    }

    testTemplate() {
       
        this.template = this.incident.incidentTestTemplates;

    }

    navigateToList() {
        this.route.navigate(['/recruitment/event/' + this.view, { page: this.pgNumber }]);
    }

    textSearch() {
        this.incidantApplicantsFiltered = this.incident.incidentApplicants.filter(e =>
            e.firstName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.lastName.toLowerCase().includes(this.searchString.toLowerCase())
            || e.email.toLowerCase().includes(this.searchString.toLowerCase())
            || e.mobileNumber.toLowerCase().includes(this.searchString.toLowerCase())
        );
    }

    submit() {

        if(this.isTestCreated==true){
            this.incident.incidentApplicants = [];
            this.incident.incidentTestTemplates = [];
            this.incident.participatingInstitutes = [];
            this.incident.incidentTests = [];
            this.incidantApplicantsFiltered.forEach(element => {
                element.isTestScheduled = (element.dummyisTestScheduled ? "Y" : "N");
                element.testScheduledDt = new Date();
                this.incident.incidentApplicants.push(element)
            });
            this.service.updateIncident(this.incident.id, 'schedule-test', this.incident).subscribe(x => {
                this.route.navigate(['/recruitment/event/' + this.view, { page: this.pgNumber }]);
            },
                error => {
                    this.errorMsg = JSON.parse(error._body);
                    this.msg = { type: 'danger', text: this.errorMsg.message }
                })
        }else{
            this.Alertmsg = { type: 'danger', text:'One or more test have not been imported' }
        }

       
    }

    reallocate() {
        this.incident.incidentApplicants = [];
        this.incident.incidentTestTemplates = [];
        this.incident.participatingInstitutes = [];
        this.incident.incidentTests = [];
        this.completed.forEach(element => {
            element.isReallocate = (element.dummyisReallocate ? "Y" : "N");

            if (element.isReallocate == "Y" && element.reallocateMessage != 'not-scheduled' || element.reallocateMessage == null) {
                element.reallocateMessage = 're-trigger';
                element.testScheduledDt = new Date();
                this.incident.incidentApplicants.push(element)
            }
        });
        this.service.updateIncident(this.incident.id, 'reallocate-test', this.incident).subscribe(x => {
            this.route.navigate(['/recruitment/event/' + this.view, { page: this.pgNumber }]);
        })
    }

    onSort({ column, direction }: SortEvent) {
        // resetting other headers
        this.headers.forEach(header => {
            if (header.sortable !== column) {
                header.direction = '';
            }
        });

        if (direction === '') {
            this.incidantApplicantsFiltered = this.sort;
        } else {
            this.incidantApplicantsFiltered = [...this.incidantApplicantsFiltered].sort((a, b) => {
                const res = compare(a[column], b[column]);
                return direction === 'asc' ? res : -res;
            });
        }
    }

    changeActive(id: any) {
        this.binding = id;
    }

    downloadCsv() {
        var jsonObj = this.incidentApplicantPdf;
        var columns = Object.keys(jsonObj);
        this.service.downloadFile(this.incidantApplicantsFiltered, 'jsontocsv', columns);
    }

    moveToUnassigned(id, applicantId) {
        this.service.moveToUnassigned(id, applicantId).subscribe(x => {
            this.ngOnInit();
            this.Alertmsg = { type: 'success', text: "Update Successfully" }
        }, error => {
            this.msg = JSON.parse(error._body);
            this.Alertmsg = { type: 'danger', text: this.msg.message }
        })
    }
    checkUncheckAll() {
        for (var i = 0; i < this.incidantApplicantsFiltered.length; i++) {
          this.incidantApplicantsFiltered[i].dummyisTestScheduled = this.masterSelected;
        }
    }
    isAllSelected() {

        this.masterSelected = this.incidantApplicantsFiltered.every(function(mg1:any) {
          return mg1.dummyisTestScheduled == true;
        })
    }
    changeEvent(event) {
        if (event.target.checked) {
            this.toggleBool= false;
        }
        else {
            this.toggleBool= true;
        }
    }


}