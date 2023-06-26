import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { OfficeCalendarService } from '../office-calendar.service'
import { ActivatedRoute,Params,Router } from '@angular/router';
import { Calendar } from '../../vo/calendar'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: '',
    templateUrl: 'office-calendar-list.component.html'
})

export class OfficeCalendarListComponent implements OnInit {
    constructor(
        private activedRouter:ActivatedRoute,
        private modalService: NgbModal,
        private service:OfficeCalendarService,
        private route: Router
    ) { }

    @ViewChild('Create') Create: TemplateRef<any>;
    modalReference:any;
    curDate:Date;
    year:number;
    disYear:number;
    yearId1:any;
    yearId2:any;
    yearId3:any;
    yearId:any;
    offices:any=[];
    officeCal:Calendar;
    page :number = 1;
    pageSize :number = 10;
    ngOnInit() { 
        this.curDate = new Date();
        this.yearId1 = new Calendar();
        this.yearId2 = new Calendar();
        this.yearId3 = new Calendar();
        this.year = this.curDate.getFullYear();
        this.service.getMultipleData(this.year).subscribe(x=>{
            this.yearId1=x[0];
            this.yearId2=x[1];
            this.yearId3=x[2];     
            this.findList(this.yearId2.id,'');
        })     
    }

    findList(yearId,code:string){   

        if(yearId!=null){
            this.yearId=yearId;
            this.service.getOffices().subscribe(x=>{
                x.forEach(off => {
                    off.calStatus='';
                    off.totalCalendarDays='';
                    off.weekdayCount='';
                    off.holidayCount='';
                    off.weekendCount='';
                    off.calYearId='';
                    off.totalCalculatedDays='';
                    off.calendarHeaderId=null
                });
                this.offices=x;           
              })
            this.service.getOfficeCalendarByYear(yearId).subscribe(x=>{           
                x.forEach(element => {                
                    for(let off of this.offices){                 
                        if(off.id==element.officeId&&element.calYearId==yearId){
                            off.calStatus=element.calStatus;
                            off.totalCalendarDays= element.totalCalendarDays;
                            off.weekdayCount= element.weekdayCount;
                            off.holidayCount=element.holidayCount;
                            off.weekendCount=element.weekendCount;
                            off.calYearId=yearId;
                            off.totalCalculatedDays=element.totalCalculatedDays;
                            off.calendarHeaderId=element.id;
                        }
                    }
                });  
                console.log(this.offices);          
            })   
        }else{
                let year:number;
                year= Number(code.replace(/\D/g,''));
                console.log(year);
                this.officeCal = new Calendar();
                this.disYear=year
                this.officeCal.calCode = 'CAL-'+year;
                this.officeCal.fromDt=year+'-01-01'
                this.officeCal.toDt=year+'-12-31'
                console.log(this.officeCal);
                this.modalReference = this.modalService.open(this.Create, {size: 'lg'});
           
        }
    }
    createCal(){
        this.service.createCalendarYear(this.officeCal).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
        })
    }
    start(view,officeId){
        this.route.navigateByUrl('operation/office-calendar/'+view+'/'+this.yearId+'/'+officeId);
    }
    view(view,id){
        this.route.navigateByUrl('operation/office-calendar/'+view+'/'+id);
    }
}