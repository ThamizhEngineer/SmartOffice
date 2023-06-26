import { Component, OnInit,ViewChild, TemplateRef} from '@angular/core';
import { OrgAnalysisService } from '../org-analysis.service';
import { environment } from '../../../../../environments/environment';
import { OrgAnalysis,resultEmps } from '../vo/org-analysis';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
 

export const shmsg={
    'EXCEED':'fa-star text-warning fa-2x',    
    'MET':'fa-thumbs-up text-success fa-2x',
    'NOT-MET':'fa-thumbs-down fa-2x'}
  export const status={
      'Y':'success',
      'high':'success',
      'N':'danger',
      'mid':'warning',
      'low':'danger'
  }
  
  export const Ratemsg={'1':'Has not met the requirements',
    '2':'Has met some but not all of the requirements',
    '3':'Has met the requirements',
    '4':'Has exceeded some of the requirements and met all others',
    '5':'Has exceeded all of the requirements'}
  

@Component({
    selector: '',
    templateUrl: 'org-analysis.component.html'
})

export class OrgAnalysisComponent implements OnInit {
    
    @ViewChild('empOrgAnalysis') empOrgAnalysis: TemplateRef<any>;
    @ViewChild('selectYear') selectYear: TemplateRef<any>;

    // refresh:[OrgAnalysis];
    documentApiUrl: string = environment.documentApiUrl;

    orgAnalysis:any=[];
    dummyorgAnalysis:any=[];
    deviation:any;
    modalReference:any = null;

    empOrgAnalysises:any=[];
    orgAnalysises:OrgAnalysis;
    statues:any=status;
    rating:any=Ratemsg;
    icon:any=shmsg;
    errorMsg:any;

    yearId:string;
    fyName:string;
    msg:any;
    refreshId:string='refresh-org-analysis';
    statusId:string='status-check';

    fyYears:any=[];
    page :number = 1;
    pageSize :number = 5;
    constructor(
        private service:OrgAnalysisService,
        private modalService: NgbModal
    ) { }

    ngOnInit() {             
        this.service.getRefesh(this.statusId).subscribe(x=>{
            console.log(x);
            if(x[0]=='success'){                
                this.view('');
            }            
        })
       

        this.service.getFyYears().subscribe(x=>{
            this.fyYears=x;
        })
    }


    view(type){
        this.orgAnalysis=[];
        this.dummyorgAnalysis=[];
        this.service.getRefesh(this.refreshId).subscribe(x=>{
            this.dummyorgAnalysis=x;
        x.forEach(element => {   
            element.dummyDesc=element.goalDesc.substring(0,35)+'......';                 
      switch(type){
        case 'Functional':           
                if(element.strategy=='functional'){
                 this.orgAnalysis.push(element);
                }     
        break;
        case 'Non-Functional':          
                    if(element.strategy=='non-functional'){
                     this.orgAnalysis.push(element);
                    }       
        break;
        case 'EXCEED':            
                    if(element.acheivVerdictName=='EXCEED'){
                     this.orgAnalysis.push(element);
                    }        
            break;
        case 'MET':          
                    if(element.acheivVerdictName=='MET'){
                     this.orgAnalysis.push(element);
                    }         
            break;
        case 'NOT-MET':          
                    if(element.acheivVerdictName=='NOT-MET'){
                     this.orgAnalysis.push(element);
                    }          
            break; 
        case 'Technical':          
            if(element.isTechnical=='Y'){
             this.orgAnalysis.push(element);
        }          
            break;  
        case 'Behavioral':          
            if(element.isTechnical=='N'){
             this.orgAnalysis.push(element);
        }          
            break;            
        default:
            this.orgAnalysis.push(element);
            break;
      }
      console.log(this.orgAnalysis);
    });  
    this.yearId=this.orgAnalysis[0].fyId;
    this.fyName=this.orgAnalysis[0].fyName;
});  
     }

     refresh(){
        this.service.refresh(this.yearId).subscribe(x=>{
            console.log(x)  
            if(x[0]==null){
                this.msg = { type: 'danger', text: 'Not Available for this year' }
            }else{
                this.statusId=x[0].docId
                this.refreshId=x[1].docId
                this.view('');   
            }                                            
        },error=>{
            this.errorMsg=JSON.parse(error._body);            
            this.msg = { type: 'danger', text: this.errorMsg.message }  
        })
     }

     close(){
        this.modalReference.close();
    }

    get countOfAll(){
        return this.dummyorgAnalysis
    }
    get functionalCount(){
        return this.dummyorgAnalysis.filter(x=>x.strategy=='functional');   
    }
    get nonFunctionalCount(){
        return this.dummyorgAnalysis.filter(x=>x.strategy=='non-functional');   
    }
    get technicalCount(){
        return this.dummyorgAnalysis.filter(x=>x.isTechnical=='Y');   
    }
    get nonTechnicalCount(){
        return this.dummyorgAnalysis.filter(x=>x.isTechnical=='N');   
    }


    showEmpOrgAnalysis(orgAnalysis){  
        this.orgAnalysises=new OrgAnalysis;  
        this.orgAnalysises=orgAnalysis;
       this.modalReference = this.modalService.open(this.empOrgAnalysis, {size: 'lg'});	
     }


}