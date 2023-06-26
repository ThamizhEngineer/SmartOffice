import { Component, OnInit,ViewChild, TemplateRef} from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { AppraisalService } from '../appraisal.service';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
import { status_css } from '../../../vo/status';
import {Observable} from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from '../../../vo/employee';
import { AppraisalHdr } from '../../vo/appraisal';
import { AppraisalReview } from '../../vo/appraisal-review';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import { AppraisalSetting } from '../../vo/appraisal-setting';

@Component({
    selector: '',
    templateUrl: 'appraisal-list.component.html'
})

export class AppraisalListComponent implements OnInit {
  
    @ViewChild('alert') alert: TemplateRef<any>;
    @ViewChild( 'appSettings') appSettings:TemplateRef<any>;

    formatterManager = (x:{empName: string}) =>x.empName;
	profileAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    
  
    apprisals:any=[];
    view:string;
    yearId:string;
    fyYears:any=[];
    user:User;
    statues:any=status_css;
    officename:any='';
    empId:string=null;
    empName:string="";
    n1Id:string='';
    n1Name:string="";
    n2Id:string='';
    n2Name:string="";
    desName:any='';

    checkView:string="PENDING";

    appraisal:AppraisalHdr;
    appraisals:[AppraisalReview];
    employee:Employee;

    msg:any;
    errorMsg :any;
    search:boolean=false;
    searchByFisCode=false;
    fiscalCode:string;
    minOrgGoalsUm:string;
    minOrgGoalsMm:string;
    minOrgGoalsOthers:string;

    designames:any;    
    offices:any=[];
    allEmployees: [Employee];
    employeesList: [Employee];
    appraisalSetting:AppraisalSetting;
    appObj:AppraisalSetting;
    modalReference:any = null;
    page :number = 1;    
    pageSize :number = 10

    constructor(
        private router:Router,
        private activatedRoute:ActivatedRoute,
        private service:AppraisalService,
        private userService:UserService,
        private modalService: NgbModal
    ) { }

    ngOnInit() {
        this.allEmployees = [new Employee]
        this.employeesList = [new Employee]
        this.appraisals = [new AppraisalReview]
        this.appraisalSetting = new AppraisalSetting();
        this.appObj = new AppraisalSetting;
        this.user = this.userService.getCurrentUser();
        this.activatedRoute.params.subscribe(params=>{            
            this.view=params.view;
            });
    //  this.service.getAppraisals().subscribe(x=>{
    //      this.apprisals=x;      
    //  });
    this.service.getOffices().subscribe(x=>{
        this.offices=x;
    })
     this.service.getFyYears().subscribe(x=>{
         this.fyYears=x;
     }); 

     this.service.getEmployees().subscribe(emps=>{        
         this.allEmployees=emps;
         this.allEmployees.forEach(element => {
            element.isIniteated=false;
        });        
     })
     this.service.getDesignations().subscribe(desig=>{
         this.designames=desig;
     })

     }


     get N1list(){        
         return this.apprisals.filter(x=>x.n1EmpId==this.user.employeeId);
     }
     get  N2list(){
        return this.apprisals.filter(x=>x.n2EmpId==this.user.employeeId);
    }
    empSelected($event){
         this.empId= $event.id; 
         this.empName=$event.empName
    }
    n1Selected($event){
        // console.log($event)
        this.n1Id= $event.id;
        this.n1Name=$event.empName;      
    }
    n2Selected($event){
    this.n2Id= $event.id; 
    this.n2Name=$event.empName;     
    }

     empDetailPage(){
         this.service.getAppraisalByFyIdAndEmpId(this.yearId,this.user.employeeId).subscribe(x=>{
            
             if(x[0]!=null){
                this.router.navigateByUrl('/my-space/appraisal/'+this.view+'/detail/'+x[0].id);  
             }else{
                this.msg = { type: 'danger', text: "Apprisal Not start for this Year" }               
             }            
         },error=>{
             console.log("Apprisals Not Initiate for this Year");
         })
     }

     advSearch(empId){
        this.search=true;
        console.log(this.yearId);
        if(empId==null){
            this.service.getAppraisalByFyId(this.yearId).subscribe(x=>{
                this.apprisals=x;
            })              
        }else{
            this.service.getAppraisalByFyIdAndEmpId(this.yearId,empId).subscribe(x=>{
                this.apprisals=x;
                })
        }
     }

    //  searchEmpName,searchDesigName,searchEmailId,searchContactMobileNo,serachOfficeName,searchEmpCode,searchEmpStatus,serachId,n1EmpId,n2EmpId
    
     searches(){
        this.search=true;
        
        this.service.getEmployees().subscribe(x=>{
            console.log(x);        
            this.employeesList=x;
            this.searchHr();
        })        

        // this.service.hr1GetEmployee('',this.desName,'','',this.officename,'','',this.empId,this.n1Id,this.n2Id).subscribe(x=>{
        //     console.log(x);        
        //     this.employeesList=x.memployeeList;
        //     this.searchHr();
        // })                                      
     }

     searchHr(){
        this.service.getAppraisalByFyId(this.yearId).subscribe(x=>{
            x.forEach(appr => {
                for( let emp of this.employeesList){                                                        
                    var check = emp.id==appr.empId;
                    //var reCheck = emp.id==appr.empId &&                
                   // emp.reIniteated=reCheck;     
                   
                   
                    if(check){
                        emp.isAchvmt = appr.appraisalAchvmtStatusCode!=null                       
                        emp.isIniteated=check;
                        emp.appraisalId=appr.id;
                        emp.reIniteated=appr.appraisalTargetStatusCode=='APPROVED' || appr.appraisalTargetStatusCode=='CREATED'                        
                    }                                        
                }                               
            });            
         });   
     }

     searchFisCode(){
        this.searchByFisCode=true;
         if(this.yearId==null){
            alert("Please Select Fiscal-Year First");
         }
         if(this.searchByFisCode=true && this.yearId!=null){
            this.service.getAppraisalByFisYearId(this.yearId).subscribe(x=>{
                this.appraisalSetting=x;           
                if(x!=null){
                   this.fiscalCode = x.fiscalCode;
                }   
            })
            this.modalReference = this.modalService.open(this.appSettings ,{size :'lg'});
         }
     }

     updateAppSetting(i,data){         
        this.appraisalSetting = data;        
        if(i!=null){
            this.service.updateAppraisalSetting(i,this.appraisalSetting).subscribe(x=>{

            })
        } 
        this.modalReference.close(); 
     }
     createAppSetting(){
        this.appObj.fiscalYearId=this.yearId;
        this.appObj.minOrgGoalsUm=this.minOrgGoalsUm;
        this.appObj.minOrgGoalsMm=this.minOrgGoalsMm;
        this.appObj.minOrgGoalsOthers=this.minOrgGoalsOthers;        
        this.service.createAppraisalSetting(this.appObj).subscribe(x=>{

        })
        this.modalReference.close();
     }

     reset(){
         this.empId=null
         this.empName=null
         this.n1Name=""
         this.n2Name=""
         this.yearId=null;
         this.search=false
         this.allEmployees=[new Employee]
         this.ngOnInit();
     }

     initiate(){
        this.appraisal=new AppraisalHdr();
        this.appraisal.fyId=this.yearId
        this.appraisal.empId=this.employee.id.toString();  
        this.appraisal.settleUserGroupId=this.employee.hr1Id;
        this.appraisal.settleUserGroup2Id=this.employee.hr2Id;
        this.appraisal.n1EmpId=this.employee.n1EmpId;
        this.appraisal.n2EmpId=this.employee.n2EmpId;
        this.appraisal.officeId=this.employee.officeId;
        this.appraisal.designationId=this.employee.designationId;
        this.service.createAppraisal(this.appraisal).subscribe(x=>{
            this.modalReference.close();
            this.searches();
        },error=>{
            console.log("server error")
        })
     }

     initiateAchivement(employee){
        this.employee = new Employee;
        this.employee=employee;
        let appra=new AppraisalHdr();
        // this.appraisal.fyId=this.yearId
        // this.appraisal.empId=this.employee.id.toString();  
        // this.appraisal.settleUserGroupId=this.employee.hr1Id;
        // this.appraisal.settleUserGroup2Id=this.employee.hr2Id;
        // this.appraisal.n1EmpId=this.employee.n1EmpId;
        // this.appraisal.n2EmpId=this.employee.n2EmpId;
        // this.appraisal.officeId=this.employee.officeId;
        // this.appraisal.designationId=this.employee.designationId;
        appra.id=this.employee.appraisalId;
        
        this.service.updateAppraisalAchiv('initiate',appra.id,this.appraisals).subscribe(x=>{
            this.searches();
            this.msg = { type: 'success', text: 'Achievement Started For'+this.employee.empName }
        },error=>{
            this.errorMsg=JSON.parse(error._body);
            this.modalReference.close();
            this.msg = { type: 'danger', text: this.errorMsg.message } 
        });       
     }

     initiateProcess(employee){
         this.employee = new Employee;
         this.employee=employee;
        this.modalReference = this.modalService.open(this.alert, {size: 'sm'});
     }

     hrDetailView(empId){
        this.service.getAppraisalByFyIdAndEmpId(this.yearId,empId).subscribe(x=>{            
           this.router.navigateByUrl('/my-space/appraisal/'+this.view+'/detail/'+x[0].id);  
        },error=>{
            console.log("Apprisals Not Initiate for this Year");
        })
     }


    navigateToDetailPage(id){
        this.router.navigateByUrl('/my-task/appraisal/'+this.view+'/detail/'+id);       
       }

    navigateToReviewPage(){
        this.router.navigate(['/my-task/appraisal/review/'], {queryParams:{view:'emp'}});
    }
}