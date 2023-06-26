import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { IncidentService } from '../incident.service';
import { ActivatedRoute,Params,Router } from '@angular/router';
import { Incident,IncidentTestTemplate } from '../../../vo/incident'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { status_css } from '../../../vo/status'
import { saveAs } from 'file-saver';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';


@Component({
    selector: 'incident',
    templateUrl: 'incident.component.html'
})

export class IncidentComponent implements OnInit {
    @ViewChild('testTemplate') testTemplate: TemplateRef<any>;
    @ViewChild('deleteTests') deleteTests: TemplateRef<any>;
    itt:Array<IncidentTestTemplate>;
    incidentApplicantTemplateQuestions:any=[];
    incidentList:any=[];
    inc:Incident;
    view:string;
    screenName:string;
    training:any=[];
    page :number = 1;
    statuses:any=status_css;
    pageNumber:number=1;    
    collectionSize:number;
    user:User;
    errorMsg:any;
    saveMsg:any;
    isDir:boolean=false;
    isHr1:boolean=false;
    pageSize :number = 4
    constructor(
        private activedRouter:ActivatedRoute,
        private service: IncidentService,
        private route: Router,
        private _userService:UserService,
        private modalService: NgbModal
    ) { }

    ngOnInit() {    
        this.user = this._userService.getCurrentUser();        
        if(this.activedRouter.params['_value']['view']){           
            this.activedRouter.params.subscribe(x=>{   
                this.user.userGroupMappings.forEach(element => {
                    if(element.isHrL1=='Y'||element.isHrL2=='Y'){              
                        this.isHr1=true;                               
                    }if(element.isDir=='Y')  {
                        this.isDir=true;                                
                    }                           
                });                 
                if(x.page!=null){
                    this.pageNumber=x.page;
                }
                this.view=x.view;                        
                if(x.view=='recruitment'){  
                    this.screenName='Recruitment';                       
                    this.onInit();                      
                }
                if(x.view=='training'){    
                    this.screenName='Training';                    
                    this.onInit();                                                             
                }
            });   
                                  
        }    
    }    


    onInit(){
        // let incidentMap = new Map();   
        this.incidentList=[];
        if(this.isDir==true){
            this.service.getIncidentForDir().subscribe(y=>{
                y.forEach(element => {
                    if(element.incidentType==this.screenName){
                        this.incidentList.push(element);
                        // incidentMap.set(element.id, element)
                        // console.log(incidentMap)
                    } 
                    // this.incidentList = incidentMap.values();
                   });                                                                                  
                
                this.incidentApplicantTemplateQuestions=[];
            });
        }
        else if(this.isHr1==true){
            this.service.getIncidents().subscribe(x => { 
                x.forEach(element => {
                 if(element.incidentType==this.screenName){
                     this.incidentList.push(element);
                    //  incidentMap.set(element.id, element)
                    //  console.log(incidentMap)
                 } 
                //  this.incidentList = incidentMap.values();
                });                                                                               
             })
             this.incidentApplicantTemplateQuestions=[];            
        }
        
    }

    pageChanged(pN: number): void {
        this.pageNumber = pN;
      }
      generateTemplate(id,incident:Incident){
          let testPresent:Boolean=false;
            incident.incidentTestTemplates.forEach(element => {
                if(element.incidentApplicantTemplateQuestions.length==0){
                    testPresent=true;
                }
            });
            if(testPresent==true){
                this.service.updateIncident(id,'generate-template',incident).subscribe(x => {
                    this.incidentList=x;
                    this.ngOnInit();
                },
                error => {                       
        this.errorMsg = JSON.parse(error._body);
        this.saveMsg = { type: 'danger', text: this.errorMsg.message }
                }) 
            }else{
                for (let index = 0; index < this.incidentList.length; index++) {
                    if(this.incidentList[index].id==id){
                        this.incidentList[index].msg= { type: 'info', text: "Template Already Generated..!!" }   
                    }                   
                   
                }
                              
            }
        
    }
    isTestAttend:boolean;

    deleteIncidenTest(id){
        this.inc = new Incident();            
            this.service.getIncidentById(id).subscribe(x=>{
                this.inc=x;
                this.modalReference = this.modalService.open(this.deleteTests, { size: 'lg' });
            });                                         
    }

    get incidentStartTest(){
        return this.inc.incidentTests.filter(x=>x.testStatus=='In-Progress')
    }
    get incidentCompleteTest(){
        return this.inc.incidentTests.filter(x=>x.testStatus=='Completed')
    }

    deleteTest(type,id){   
        if(type=='test'){
            this.modalReference.close();
        }    
        this.service.deleteTestAndTempalte(type,id).subscribe(x=>{
            this.ngOnInit();
            this.saveMsg = { type: 'warning', text: x }
        },
        error => {                       
            this.errorMsg = JSON.parse(error._body);
            this.saveMsg = { type: 'danger', text: this.errorMsg.message }
        })
    }

    createNew(){
        this.route.navigateByUrl('recruitment/event/'+this.view+'/new-incident')
    }
    
    navigateToAddApplicant(id) {
        this.route.navigate(['/recruitment/event/recruitment/add-applicant/'+id,{page:this.pageNumber}]);
     //   this.route.navigateByUrl('recruitment/event/recruitment/add-applicant/'+id)
    }
    navigateToShortList(id) {
        this.route.navigate(['/recruitment/event/recruitment/short-list/'+id,{page:this.pageNumber}]);
       // this.route.navigateByUrl('recruitment/event/recruitment/short-list/'+id)
    }

    navigateToFilterApplicant(id){
        this.route.navigate(['/recruitment/event/recruitment/test-eligibility/'+id,{page:this.pageNumber}]);
        //[routerLink]="['/recruitment/event/recruitment/test-eligibility/',c.id]"
    }

    navigateToInterview(id) {
        this.route.navigate(['/recruitment/event/recruitment/schedule-interview/'+id,{page:this.pageNumber}]);
       // this.route.navigateByUrl('recruitment/event/recruitment/schedule-interview/'+id)
    }
    navigateToImportApplicant(id) {
        this.route.navigate(['/recruitment/event/'+this.view+'/import-applicant/'+id,{page:this.pageNumber}]);
       // this.route.navigateByUrl('recruitment/event/'+this.view+'/import-applicant/'+id)
    }
    navigateToTestReport(id){
        this.route.navigate(['/recruitment/event/'+this.view+'/test-report/'+id,{page:this.pageNumber}]);
        //this.route.navigateByUrl('recruitment/event/'+this.view+'/test-report/'+id)
    }

    navigateToTestSchedule(id){
        this.route.navigate(['/recruitment/event/'+this.view+'/test-schedule/'+id,{page:this.pageNumber}]);
       // this.route.navigateByUrl('recruitment/event/'+this.view+'/test-schedule/'+id)
    }

    navigateToDetailPage(id:number){
    this.route.navigate(['/recruitment/event/'+this.view+'/edit/'+this.view+'/'+id,{page:this.pageNumber}]);
     //  this.route.navigateByUrl('recruitment/event/'+this.view+'/edit/'+this.view+'/'+id)
   }

   navigateToEmployeeConversion(id){
    this.route.navigate(['/recruitment/event/recruitment/employee-conversion/'+id,{page:this.pageNumber}]);
     //  this.route.navigateByUrl('recruitment/event/recruitment/employee-conversion/'+id)
   }

   navigateToTrainingProgress(id){
    this.route.navigate(['/recruitment/event/'+this.view+'/training-progress/'+id,{page:this.pageNumber}]);
      // this.route.navigateByUrl('recruitment/event/'+this.view+'/training-progress/'+id);
   }
   modalReference:any = null;
   getTemplates(i){       
       this.itt = i.incidentTestTemplates;
       this.modalReference = this.modalService.open(this.testTemplate, { size: 'lg' });
}
    getQuestion(q){
        this.incidentApplicantTemplateQuestions=q;
    }

    exportApplicant(id){        
        this.service.exportApplicant(id).subscribe(x=>{            
            saveAs(x.blob(), "export.xls");
        })
    }

    exportResult(id){        
        this.service.exportResult(id).subscribe(x=>{            
            saveAs(x.blob(), "exportResult.xls");        
        })
    }

    exportTestResult(id){
        this.service.exportTest(id).subscribe(x=>{
            saveAs(x.blob(), "exportTest.xls");
        })
    }
    
}

