import { Component, OnInit,ViewChild, TemplateRef  } from '@angular/core';
import { InAppNotfn } from '../../../../theme/default/header-nav/_vo/InAppNotfn';
import { InAppNotfnService } from '../../../../theme/default/header-nav/in-app-notfn.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    templateUrl: 'index.component.html'
})

export class IndexComponent implements OnInit {
    @ViewChild('indexModule') indexModule: TemplateRef<any>;
    messages: Array < InAppNotfn > ;
    message:InAppNotfn;
    userName: string;
    msgCount: any;
    interval: any;
    modalReference:any = null;

    constructor(private inAppNotfnService: InAppNotfnService,private modalService: NgbModal) {}

    ngOnInit() {
        console.log("notif started")
        this.notfnMessage();
    }

    notfnMessage() {
        this.inAppNotfnService.getMyInAppNotfn().subscribe(x => {
            this.messages = x;
            this.msgCount = this.messages.length;
            console.log(this.messages)

            this.interval = setInterval(() => {
                this.messages = new Array < InAppNotfn > ();
                this.inAppNotfnService.getMyInAppNotfn().subscribe(x => {
                    this.messages = x;
                    console.log(this.messages)
                    this.msgCount = this.messages.length;
                });
            }, 600000)
        })
    }

    getIndex(index){
        this.message=index;
        this.modalReference = this.modalService.open(this.indexModule, { size: 'lg' });
    }

}