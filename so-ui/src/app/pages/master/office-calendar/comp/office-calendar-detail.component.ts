import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { ActivatedRoute,Params,Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { OfficeCalendarService } from '../office-calendar.service';
import { CommonService } from '../../../../shared/common/common.service';

import { Calendar } from '../../vo/calendar';
import { CalendarHeader } from '../../vo/calendarHeader';
import { OfficeCalendar } from '../../vo/officeCalendar';

@Component({
    selector: '',
    templateUrl: 'office-calendar-detail.component.html'
})

export class OfficeCalendarDetailComponent implements OnInit {
  
    constructor(
        private activedRouter:ActivatedRoute,
        private service:OfficeCalendarService,
        private commonService:CommonService,
        private modalService: NgbModal,
        private route: Router,
    ) { }

    @ViewChild('viewCal') viewCal: TemplateRef<any>;
    @ViewChild('warningMsg') warningMsg: TemplateRef<any>;


    view:any;
    officeId:any;
    yearId:any;
    calendarCode:any;
    officeName:any;
    yearCode:any;
    calendarHeader:CalendarHeader;
    modalReference:any = null;
    groupMonth:any=[];
    officeCalendar:[OfficeCalendar]
    calendarSetup:any;
    eventName:any;


    ngOnInit() {
        this.calendarHeader = new CalendarHeader();
        this.officeCalendar = [new OfficeCalendar];
        if (this.activedRouter.params['_value']['view']) {
            this.activedRouter.params.subscribe(params=>{
                this.view=params.view;
                this.officeId=params.officeId;
                this.yearId=params.yearId;
                if(this.view=='start-calendar'){
                    this.service.getCalendarYearByid(params.yearId).subscribe(x=>{
                        this.yearCode=x.calCode
                        console.log(x);
                    })
                    this.service.getOfficeById(params.officeId).subscribe(x=>{
                        this.officeName=x.officeName;
                        console.log(x);
                    })
                }

                if(this.view=='view'){
                   
                    this.service.getOfficeCalendarHeaderById(params.id).subscribe(x=>{
                        this.calendarHeader=x;    
                        this.officeId=this.calendarHeader.officeId;
                        this.yearId=this.calendarHeader.calYearId;          
                        this.getMonth();                                  
                    })
                }
            })
           
        }
     }

     getMonth(){
        this.service.groupByMonth(this.calendarHeader.officeId,this.calendarHeader.calYearId).subscribe(x=>{
            x.forEach(element => {
                element.totalDays=Number(element.weekdayCount)+Number(element.weekendCount)+Number(element.holidayCount);
            });
            this.groupMonth=x;
        })
     }

     upload(event,docTypeCode:any){
        let fileList: FileList = event.target.files;        
        if (fileList.length > 0) {  
            let files: File = fileList[0];          
			 this.service.uploadHolidays(files, docTypeCode,this.calendarHeader.officeId,this.calendarHeader.calYearId).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {                      
                        this.ngOnInit();                                        
                    },
                    error => console.log(error)
                )
        }
      }

      warning(name){
          this.eventName=null;
        this.eventName=name;
        this.modalReference = this.modalService.open(this.warningMsg, {size: 'sm'});
      }

      viewOffCal(officeId,yearId,month){
          this.service.getByMonth(officeId,yearId,month).subscribe(x=>{
              x.forEach(element => {
               if(element.isRestrictedHoliday=='N'){
                element.isRestrictedHoliday=null;
               }   
              });
              this.officeCalendar=x
          })
        this.modalReference = this.modalService.open(this.viewCal, {size: 'lg'});
      }

     setUp(eventType){
         this.calendarSetup = {'weekend-rule': eventType };
         console.log(this.calendarSetup)
         this.service.calendarSetUp(this.officeId,this.yearId,this.calendarSetup).subscribe(x=>{
            this.route.navigateByUrl('master/office-calendar')
         })
     }

     get calendarValidation(){

        let count = 0;

        this.officeCalendar.forEach(x => {
            if(x.dayType=='holiday'&&x.holidayName===null||x.holidayName===''&&x.remarks===null||x.remarks===''){
                count = +1;
            }
        });

         return count;
     }

     complete(){
         this.service.completeCalendar(this.calendarHeader.id,this.calendarHeader).subscribe(x=>{
             this.ngOnInit();
         })
     }

     save(){
         this.modalReference.close();
         this.officeCalendar.forEach(element => {
             if(element.dayType=='holiday'){
                 element.isRestrictedHoliday= (element.isRestrictedHoliday ? "Y" : "N");
             }
         });

         for(let calendar of this.officeCalendar){
             this.service.updateOfficeCalendar(calendar.id,calendar.officeId,calendar.calYearId,calendar).subscribe(x=>{
                 
             })
         }
         this.ngOnInit();
     }

}