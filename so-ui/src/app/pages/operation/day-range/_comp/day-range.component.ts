import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DayRangeService } from '../day-range.service';
import { DayRange } from '../vo/day-range';

@Component({
    selector: 'day-range',
    templateUrl: './day-range.component.html'
})

export class DayRangeComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;

    constructor(private router:Router,private modalService:NgbModal,private service:DayRangeService,private route:ActivatedRoute) { }
    //  rows:Array<DayRange>;
    rows:any=[];
     dayrange:DayRange;
     saveMsg:any;
     page :number = 1;
     pageSize :number = 10;
    ngOnInit() { 
        this.getData();
        this.dayrange=new DayRange();
    }
    getData(){
        this.service.getDayRanges().subscribe(x=>
            this.rows=x);
    }
    delete(id:string){
        this.service.deleteDayRange(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Day Range Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }
    modalReference:any=null;
    modalData: any;
    create(){
        this.dayrange=new DayRange();
        this.modalData = new DayRange();
		this.modalData.lt = false;
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
    } 
    show(id:string){
        this.service.getDayRangeById(id).subscribe(x=>{
        this.dayrange=x;
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
        });
  
    }
    save(){
     
        if(this.dayrange.id){
            
                this.service.addOrUpdateLines(this.dayrange, this.dayrange.id).subscribe(x => { 
                this.rows=x;
                this.saveMsg = { 'type': 'success', 'text': "DayRange Updated Successfully" };
                this.getData();
                this.modalReference.close();
            } );             
         }
        
         else{
            this.service.addDayRange(this.dayrange).subscribe(x =>{
            this.rows=x;
            this.saveMsg = { 'type': 'success', 'text': "Day Range Created Successfully" };
            this.getData();
            this.modalReference.close();
            
        });
        }
    }
        
    
}