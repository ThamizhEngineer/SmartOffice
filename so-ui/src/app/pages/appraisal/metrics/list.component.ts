import { Component ,OnInit, ViewChild, TemplateRef} from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AppraisalService } from '../appraisal.service';
import { Metric } from './vo/metric';
import { FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
    selector:'metric-list',
    templateUrl:'list.component.html'
})

export class MetricListComponent implements OnInit{
    @ViewChild('vdetail') vdetail: TemplateRef<any>;
    rows=[]
    metric:Metric;
    modalReference: any = null;
    myGroup: FormGroup;
    mCat: FormControl;
    mname: FormControl;
    better: FormControl;
    threshold: FormControl;
    desc: FormControl;

    operator:any=[];
    page :number = 1;
    pageSize :number = 10;

    constructor(private router: Router, private http: Http,private modalService: NgbModal, private appraisalService: AppraisalService) {

    }
   
    ngOnInit(){
        this.metric= new Metric();
        this.getAll();
        this.getCategories();
        this.getUnits();
        this.validate();
        this.createForm();
        this.appraisalService.getOperator().subscribe(x=>{
            this.operator=x;
        })
    }
    getAll(){
        this.appraisalService.getAll().subscribe(x=>{
            this.rows=x;
            
        })
    }
    categoryList=[]
    unitsList=[]
    getCategories(){
        this.appraisalService.getCategories().subscribe(x=>{
            this.categoryList=x;
           
        })
    }
    getUnits(){
        this.appraisalService.getUnits().subscribe(y=>{
            this.unitsList=y;
        })
    }
    showDetail(id?: any){
        this.appraisalService.getById(id).subscribe(z=>{
            this.metric=z;
            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        })
    }
    deleteRow(id?: any){
        this.appraisalService.delete(id).subscribe( x=>{
            this.ngOnInit();
        });
    }
    createNew(){
        this.metric= new Metric();
        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }
    saveMsg:any;
    save(){
        this.appraisalService.add(this.metric).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = { 'type': 'success', 'text': "Metric Created Successfully" };
            this.getAll();
        })
    }
    update(){
        this.appraisalService.update(this.metric).subscribe(x=>{
            this.modalReference.close();
            this.saveMsg = { 'type': 'success', 'text': "Metric Updated Successfully" };
            this.getAll();
        })
    }

    validate() {
        this.mCat = new FormControl('', [Validators.required]);
        this.mname = new FormControl('', [Validators.required]);
        this.better = new FormControl('', [Validators.required]);
        this.threshold = new FormControl('', [Validators.required]);
        this.desc = new FormControl('', [Validators.required]);
    }
    createForm() {
        this.myGroup = new FormGroup({
            mCat: this.mCat,
            mname: this.mname,
            better: this.better,
            threshold: this.threshold,
            desc:this.desc
        });
    }
    

}