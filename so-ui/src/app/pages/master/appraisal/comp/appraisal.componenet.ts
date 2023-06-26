import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Units } from '../../../vo/units';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AppraisalService } from '../appraisal.service';
import { MetricCategory } from '../../../vo/metric-category';


@Component({
    selector: '',
    templateUrl: 'appraisal.componenet.html'
})

export class AppraisalComponent implements OnInit {

    @ViewChild('editunits') editunits: TemplateRef<any>;
    @ViewChild('editmetric') editmetric: TemplateRef<any>;
    
    units:any=[];
    unit:Units;

    metric:MetricCategory;
    metrics:any=[];

    mpage :number = 1;
    mpageSize :number = 8;

    upage :number = 1;
    upageSize :number = 8;
    modalReference:any;

    saveMsg:any;
    errorMsg:any;

    isEdit:boolean=false;

    constructor(
        private modalService: NgbModal,
        private service:AppraisalService
    ) { }

    ngOnInit() { 
        this.unit= new Units();
        this.metric = new MetricCategory();   
        
        this.service.getAllUnits().subscribe(x=>{
            this.units=x;
        })  
        this.service.getAllMetric().subscribe(x=>{
            this.metrics=x;
        })      
    }

    editUnits(unit){
        this.unit = new Units();
        if(unit==null){
            this.unit = new Units();
            this.isEdit=false;
        }else{
            this.service.getunitsByCode(unit).subscribe(x=>{
                this.unit=x;
                this.isEdit=true;
            })            
        }        
        this.modalReference = this.modalService.open(this.editunits, { size: 'lg' });
    }

    editMetric(id){
        if(id==null){
            this.metric = new MetricCategory();            
        }else{
            this.service.getMetricById(id).subscribe(x=>{
                this.metric=x;                
            })            
        }   
        this.modalReference = this.modalService.open(this.editmetric, { size: 'lg' });
    }

    removeUnits(code){
        this.service.deleteUnits(code).subscribe(x=>{
            this.ngOnInit();
        },error=>{

        })
    }

    saveUnits(){        
        this.service.createUnits(this.unit).subscribe(x=>{
            this.ngOnInit();
            this.modalReference.close();
            this.saveMsg = {'type': 'success', 'text': "Units Created Successfully"};
        },error=>{
            this.errorMsg = {'type': 'danger', 'text': "Units Code Already Present"};
        })
    }
   
    updateUnit(){
        this.service.updateUnits(this.unit.unitCode,this.unit).subscribe(x=>{
            this.modalReference.close();
            this.ngOnInit();
            this.saveMsg = {'type': 'success', 'text': "Units Updated Successfully"};
        },error=>{
            this.errorMsg = {'type': 'danger', 'text': "Units Code Already Present"};
        })
    }

    saveMetric(){
        this.service.createMetric(this.metric).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = {'type': 'success', 'text': "Metric Category Created Successfully"};
            this.ngOnInit();
        },error=>{
            this.errorMsg = {'type': 'danger', 'text': "Metric Code Already Present"};
        })
    }
     
    updateMetric(){
        this.service.updateMetric(this.metric.id,this.metric).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = {'type': 'success', 'text': "Metric Category Updated Successfully"};
            this.ngOnInit();
        },error=>{
            this.errorMsg = {'type': 'danger', 'text': "Metric Code Already Present"};
        })
    }

    removeMetric(id){
        this.service.deleteMetric(id).subscribe(x=>{
            this.ngOnInit();
        })
    }


}