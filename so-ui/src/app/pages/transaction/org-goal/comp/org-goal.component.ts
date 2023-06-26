import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { AppraisalService } from '../org-goal.service';
import { OrgHeader,OrgLine } from '../../../vo/org-goal';
import { saveAs } from 'file-saver';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: '',
    templateUrl: 'org-goal.component.html'
})

export class OrganisationalGoalComponent implements OnInit {
    page:number=1;
    pageSize :number = 8;
 
    year:any;
    fiscalYear:any=[];
    metrics:any=[]
    metricName:any;
    allEmployees:any;
    empId:any;
    empName:any=null;

    user:User;
    isDir:boolean=false;

    isGoal:boolean=false;

    isEdit:boolean=false;
    valid:boolean=false;

    orgHeader:OrgHeader;
    saveMsg:any;
    operators:any=[];
    myGroup: FormGroup;
    metricNames:FormControl;

    metricAC = (text$: Observable<string>) =>
    text$.pipe(
        debounceTime(200),
        distinctUntilChanged(),
        map(term => (term === '' ? this.metrics
            : this.metrics.filter(v => v.metricName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.metricCatName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.metricCatCode.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) ));
    formatter = (x: { metricName: string }) => {x.metricName};

    // formatter = (x: {metricName: string}) => x.metricName;
    // metricAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), 
    // map(term => term === '' ? [] : this.metrics.filter(v => v.metricName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.metricCatName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.metricCatCode.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) ); 

    empFormatter = (x: {empName: string}) => x.empName;
	profileAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    msg: { type: string; text: string; };

    constructor(
        private modalService: NgbModal,
        private userService:UserService,
        private service:AppraisalService
    ) { }

    ngOnInit() {
        this.user = this.userService.getCurrentUser(); 
        this.user.userGroupMappings.forEach(element => {
            if(element.isDir=='Y')  {
                this.isDir=true;                                
            }                           
        });        
        this.orgHeader = new OrgHeader();
        this.orgHeader.orgLines = [new OrgLine];
        this.service.getAllMetrics().subscribe(x=>{
            this.metrics=x;
            console.log(this.metrics);

        });
        this.service.getFiscalYear().subscribe(fy=>{
            this.fiscalYear=fy;
        })
        this.service.getEmployees().subscribe(emp=>{
            this.allEmployees=emp;
        })
        this.service.getOperator().subscribe(op=>{
            this.operators=op;
            console.log(this.operators);
        })
        this.validate();
        this.createForm();  
     }

     empSelected($event){
         this.empId=$event.item.id;
         this.empName=$event.item.empName;
     }
     addMetric(){
        let orgLines = new OrgLine();
        orgLines.metricId='';
        orgLines.metricName='';
        this.orgHeader.orgLines.push(orgLines);
     }
     removeMetric(id,i){
         if(id!=null){
             this.service.deleteOrgGoalLine(id).subscribe(x=>{
                this.saveMsg = {'type': 'success', 'text': "Deleted Successfully"} 
                this.search();
             },error=>{
                this.saveMsg = {'type': 'danger', 'text': "server error"}
             })
         }else{
            this.saveMsg = {'type': 'success', 'text': "Deleted Successfully"} 
            this.orgHeader.orgLines.splice(i,1);
         }   
     }

     metricSelected($event,i){  
         let metricId = $event.item.id;
        var response = this.orgHeader.orgLines
        .filter((orgLines: OrgLine ) => orgLines.metricId === metricId);
        if(response.length!=0){
            this.msg = { type: 'danger', text: "Duplicate record found" }
        }else{
        this.orgHeader.orgLines[i].metricId=null;
         console.log($event.item);               
        this.orgHeader.orgLines[i].metricId=$event.item.id;        
        this.orgHeader.orgLines[i].metricName=$event.item.metricName;
        this.orgHeader.orgLines[i].unitTypeSymbol=$event.item.unitTypeSymbol;
        this.orgHeader.orgLines[i].metricCategoryId=$event.item.smetricCategoryId;
        this.orgHeader.orgLines[i].operator=$event.item.better;        
        this.orgHeader.orgLines[i].threshold=$event.item.threshold;
        this.orgHeader.orgLines[i].descp=$event.item.descp;     
        console.log(this.orgHeader.orgLines[i])      
     }
    }

     search(){
        this.isGoal=true;
        this.orgHeader= new OrgHeader();
        this.orgHeader.orgLines = [new OrgLine];
        this.service.getOrgGoalByEmpId(this.year).subscribe(org=>{
            if(org==null){
                this.orgHeader= new OrgHeader();
                this.orgHeader.orgLines = [new OrgLine];
                this.orgHeader.orgLines[0].metricId='';
                this.orgHeader.orgLines[0].metricName='';
                this.isEdit=false;
            }else{
                this.isEdit=true;
                this.orgHeader=org;              
            }
               
        },error=>{
        })
     }
   
      validation(){    
        this.valid=false;
        this.orgHeader.orgLines.forEach(x => {
            if(x.metricId==null||x.metricId=='') {
                 this.valid=true;                
            }
         });
     }
     

     export(){
         this.service.exportOrgGoal(this.year).subscribe(x=>{           
            saveAs(x.blob(), "exportOrgGoal.xls");
         });
     }     
     reset(){
        this.isGoal=false;
        this.empId=null;
        this.empName=null;
        this.orgHeader= new OrgHeader();
     }

     save(){
        this.validation();
         this.orgHeader.fyYearId=this.year;
         this.orgHeader.empId= this.user.employeeId;
        this.service.createOrgGoal(this.orgHeader).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Created Successfully"}
             this.search();
         },er=>{
            this.saveMsg = {'type': 'danger', 'text': "server error"}
         })
     }
     update(){
         this.validation();
         console.log(this.valid);
         this.orgHeader.empId= this.user.employeeId;
        
         if(this.valid==false){
            this.service.updateOrgGoal(this.orgHeader.id,this.orgHeader).subscribe(x=>{
                this.saveMsg = {'type': 'success', 'text': "Updated Successfully"}
                this.search();
            },error=>{
                this.saveMsg = {'type': 'danger', 'text': "server error"}
            })
         }else if(this.valid==true){
            this.saveMsg = {'type': 'danger', 'text': "Please Enter a valied Metric Name"}
         }

            
         
      

     }
     validate(){
        this.metricNames = new FormControl('', [Validators.required]);
    }
    createForm(){
        this.myGroup = new FormGroup({
            metricNames:this.metricNames
        });		
    }
    objModify(org:OrgLine){
        this.myGroup.patchValue({
            metricNames: org.metricId
        });
    }

}
