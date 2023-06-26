import { Component, OnInit } from '@angular/core';
import { InAppNotfn } from './../../../../theme/default/header-nav/_vo/InAppNotfn';
import { InAppNotfnService } from './../../../../theme/default/header-nav/in-app-notfn.service';

@Component({
    templateUrl: 'notif.component.html'
})

export class NotificationComponent implements OnInit {
    messages: Array < InAppNotfn > ;
    userName: string;
    msgCount: any;
    interval: any;

    constructor(private inAppNotfnService: InAppNotfnService) {}

    ngOnInit() {
        console.log("notif started")
        this.notfnMessage();
    }

    notfnMessage() {
        this.inAppNotfnService.getMyInAppNotfn().subscribe(x => {
            this.messages = x;
            this.msgCount = this.messages.length;

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

}