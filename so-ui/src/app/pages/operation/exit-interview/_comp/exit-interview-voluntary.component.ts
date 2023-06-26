import { Component, OnInit, ExistingProvider } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExitInterviewService } from '../exit-interview.service';
import { ExitInterview } from '../vo/exit-interview';

const surveys = {1:"Poor", 2:"Fair", 3:"Average", 4:"Good", 5:"Excellent"}
 
@Component({
    selector: 'exit-interview-voluntary',
    templateUrl: './exit-interview-voluntary.component.html'
})
export class ExitInterviewVoluntaryComponent implements OnInit {
    exitInterview:ExitInterview;
    surveys:any=surveys;
    msg: any;

    constructor(private router:Router,private route: ActivatedRoute,private exitInterviewService:ExitInterviewService){

    }
    ngOnInit() {
        this.exitInterview= new ExitInterview();
        this.exitInterview.exitType='voluntary';
        if (this.route.params['value']['_id'] ) {
            this.route.params.switchMap((par: Params) => this.exitInterviewService.getExitInterviewById(par['_id'])).subscribe(x => {
              this.exitInterview = x;                                      
             this.formIsCondition();
            });
          }
    }
	
    formIsCondition(){
       this.exitInterview.isAutherJb=this.exitInterview.isAutherJb=="Y"?"Y":null
       this.exitInterview.isNwJbComp=this.exitInterview.isNwJbComp=="Y"?"Y":null
       this.exitInterview.isNwJbAutnmy=this.exitInterview.isNwJbAutnmy=="Y"?"Y":null
       this.exitInterview.isDissatisfctn=this.exitInterview.isDissatisfctn=="Y"?"Y":null
       this.exitInterview.isHlth=this.exitInterview.isHlth=="Y"?"Y":null
       this.exitInterview.isFamHlth=this.exitInterview.isFamHlth=="Y"?"Y":null
       this.exitInterview.isEdu =this.exitInterview.isEdu=="Y"?"Y":null
       this.exitInterview.isRetire =this.exitInterview.isRetire=="Y"?"Y":null
       this.exitInterview.isRelocat =this.exitInterview.isRelocat=="Y"?"Y":null
       this.exitInterview.isOtrsVol =this.exitInterview.isOtrsVol=="Y"?"Y":null
       this.exitInterview.isLayoff = this.exitInterview.isLayoff=="Y"?"Y":null
       this.exitInterview.isPerfmce =this.exitInterview.isPerfmce=="Y"?"Y":null
       this.exitInterview.isPorAttdce = this.exitInterview.isPorAttdce=="Y"?"Y":null
       this.exitInterview.isInsubord = this.exitInterview.isInsubord=="Y"?"Y":null
       this.exitInterview.isViolatPolicy =this.exitInterview.isViolatPolicy=="Y"?"Y":null
       this.exitInterview.isElimte = this.exitInterview.isElimte=="Y"?"Y":null
       this.exitInterview.isTemp = this.exitInterview.isTemp=="Y"?"Y":null
       this.exitInterview.isOtrsInVol = this.exitInterview.isOtrsInVol=="Y"?"Y":null
       this.exitInterview.isAntherJb = this.exitInterview.isAntherJb=="Y"?"Y":null        
    }

    formBeforSave(){
        this.exitInterview.isAutherJb=this.exitInterview.isAutherJb?"Y":"N"
       this.exitInterview.isNwJbComp=this.exitInterview.isNwJbComp?"Y":"N"
       this.exitInterview.isNwJbAutnmy=this.exitInterview.isNwJbAutnmy?"Y":"N"
       this.exitInterview.isDissatisfctn=this.exitInterview.isDissatisfctn?"Y":"N"
       this.exitInterview.isHlth=this.exitInterview.isHlth?"Y":"N"
       this.exitInterview.isFamHlth=this.exitInterview.isFamHlth?"Y":"N"
       this.exitInterview.isEdu =this.exitInterview.isEdu?"Y":"N"
       this.exitInterview.isRetire =this.exitInterview.isRetire?"Y":"N"
       this.exitInterview.isRelocat =this.exitInterview.isRelocat?"Y":"N"
       this.exitInterview.isOtrsVol =this.exitInterview.isOtrsVol?"Y":"N"
       this.exitInterview.isLayoff = this.exitInterview.isLayoff?"Y":"N"
       this.exitInterview.isPerfmce =this.exitInterview.isPerfmce?"Y":"N"
       this.exitInterview.isPorAttdce = this.exitInterview.isPorAttdce?"Y":"N"
       this.exitInterview.isInsubord = this.exitInterview.isInsubord?"Y":"N"
       this.exitInterview.isViolatPolicy =this.exitInterview.isViolatPolicy?"Y":"N"
       this.exitInterview.isElimte = this.exitInterview.isElimte?"Y":"N"
       this.exitInterview.isTemp = this.exitInterview.isTemp?"Y":"N"
       this.exitInterview.isOtrsInVol = this.exitInterview.isOtrsInVol?"Y":"N"
       this.exitInterview.isAntherJb = this.exitInterview.isAntherJb?"Y":"N" 
    }

    create(){
        if(this.exitInterview.id==null){
            this.formBeforSave();
            this.exitInterviewService.addExitInterview(this.exitInterview).subscribe(x=>{
                this.router.navigateByUrl("/operation/exit-interview/"); 
                this.msg = { type: 'success', text: "Exit Clearance Created Successfully"}
            },error=>{
                this.msg = { type: 'danger', text: "Error While Creating Exit Clearance"}
            })
        }
    }

    complete(){
        this.formBeforSave();
        this.exitInterviewService.updateExitInterview(this.exitInterview.id,'',this.exitInterview).subscribe(x=>{

        })
    }

  
    navigateToListPage(){
        this.router.navigateByUrl("/operation/exit-interview/");   
       }

       projectSelected($event){
        this.exitInterviewService.getEmployees($event.id).subscribe(x=>{
            this.exitInterview.empName=$event.empName;
            this.exitInterview.empId=$event.id;  
            this.exitInterview.empCode=$event.empCode; 
            this.exitInterview.n1ManagerName=x.managerName;
        })
              
       }

}