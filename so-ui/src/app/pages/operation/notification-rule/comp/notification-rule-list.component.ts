import { Component, OnInit, Input } from '@angular/core';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import { NotificationRuleService } from '../notification-rule.service';
import { NotificationRule } from '../vo/notification-rule';
import { ThrowStmt } from '@angular/compiler';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';


@Component({
    selector: '',
    templateUrl: 'notification-rule-list.component.html'
})

export class NotificationRuleListComponent implements OnInit {


	searchEvent = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.allNotification : this.allNotification.filter(v => v.eventName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    formatterManager = (x:{eventName: string}) =>x.eventName;

    // entitySearch = (x:{entity: string}) => x.entity;
    // entityAc = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.allNotification.filter(v => v.entity.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );

    notification: Array<NotificationRule>;
    allNotification:[NotificationRule];
    filteredNotification:Array<NotificationRule>;
    notifiCheckBox:any=[];
    eventName:string="";
    entity:string="";
    sms:string;
    email:string;
    inAppMsg:string;
    search:boolean=false;
    page :number = 1;    
    pageSize :number = 10;
    sendSmsCheck:boolean=false;
    sendEmailCheck:boolean=false;
    sendInAppNotfnCheck:boolean=false;
    id: any;
    constructor(private service: NotificationRuleService) { }

    ngOnInit(){
        this.notification = new Array<NotificationRule>();
        this.allNotification = [new NotificationRule];
        this.filteredNotification = new Array<NotificationRule>();
        this.service.getAllNotificationRules().subscribe(x=>{
            this.allNotification = x;
        })
    }

    eventSelected($event){
        this.id=$event.item.id;
        this.eventName=$event.item.eventName;
   }

    advancedSearch(entity,sms,email,inAppMsg){
        this.search=true;
        console.log(this.eventName)
        this.entity = entity;
        console.log(entity)
        if(sms=='YES'){this.sms='Y';} if(email=='YES'){this.email='Y'} if(inAppMsg=='YES') {this.inAppMsg='Y'}
        if(sms=='NO'){this.sms='N';} if(email=='NO'){this.email='N'} if(inAppMsg=='NO') {this.inAppMsg='N'}
        if(sms=='NA' || sms==''){this.sms=null;} if(email=='NA' || email==''){this.email=null} if(inAppMsg=='NA' || inAppMsg=='') {this.inAppMsg=null}
        console.log(this.sms)
        console.log(this.email)
        console.log(this.inAppMsg)         
        this.service.getEventByFilters(this.eventName,this.entity,this.sms,this.email,this.inAppMsg).subscribe(x=>{
            this.filteredNotification = x;
            console.log(this.filteredNotification)
        })
    }

    updateList(list){
        this.notification = [];
        this.notifiCheckBox = list;
        this.notifiCheckBox.forEach(element=>{
            if(element.sendSmsCheck==true){
                element.sendSms="Y";
            }else{
                element.sendSms="N";
            }
            if(element.sendEmailCheck==true){
                element.sendEmail="Y";
            }else{
                element.sendEmail="N";
            }
            if(element.sendInAppNotfnCheck==true){
                element.sendInAppNotfn="Y";
            }else{
                element.sendInAppNotfn="N";
            }
            this.notification.push(element);
        })
        this.service.updateEventFromUi(this.notification).subscribe(x=>{
                
        })
        this.ngOnInit();
    }

    reset(){
        this.eventName=null;
        this.entity=null;
        this.sms=null;
        this.email=null;
        this.inAppMsg=null;
    }
}