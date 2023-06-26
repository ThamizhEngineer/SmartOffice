import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router,ActivatedRoute } from '@angular/router';
import { AppraisalService } from '../appraisal.service';
import { AppraisalHdr,DevAction,Goal,Review,SkillObjectives } from '../../vo/appraisal';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { status_css } from '../../../vo/status';


export const shmsg={'1':'Has not met the requirements',
  '2':'Has met some but not all of the requirements',
  '3':'Has met the requirements',
  '4':'Has exceeded some of the requirements and met all others',
  '5':'Has exceeded all of the requirements'}


@Component({
    selector: '',
    templateUrl: 'appraisal-detail.component.html',
})


export class AppraisalDetailComponent implements OnInit {
    
  @ViewChild('timelines') timelines: TemplateRef<any>;
  @ViewChild('showwarning') showwarning: TemplateRef<any>;
  @ViewChild('dragDrop') dragDrop: TemplateRef<any>;
  @ViewChild('returnRes') returnRes: TemplateRef<any>;
  @ViewChild('empAlert') empAlert: TemplateRef<any>;

  formatter = (x: {metricName: string}) => x.metricName;
  orgMetricAC= (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.metricList : this.metricList.filter(v => v.metricName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
	metricAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.metrics : this.metrics.filter(v => v.metricName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
  
  productFormatter = (x: {materialName: string}) => x.materialName;
  productAc = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.products : this.products.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
  abilityFormatter = (x: {abilityName: string}) => x.abilityName;
  abilityAc = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.abilitys : this.abilitys.filter(v => v.abilityName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );

  constructor(
    private service:AppraisalService,private activatedRoute: ActivatedRoute ,private modalService: NgbModal,private route:Router
  ) { }
  
    appraisalHdr:AppraisalHdr;

    msg:any=shmsg;
    modalReference:any = null;
    timeline:any=[];
    statues:any=status_css;
    page :number = 1;
    pageSize :number = 2;
    bpage :number = 1;
    bpageSize :number = 2;

    orpage :number = 1;
    orpageSize :number = 2;
    orBpage :number = 1;
    orBpageSize :number = 2;

    skillPage:number=1;
    skillPageSize:number=5;

    action:string;
    errorMsg :any;

    metricList:any=[];
    metrics:any=[];
    Alertmsg:any;
    sortMsg:any;
    
    empView:boolean=false;
    view:string;
    len:number=0;
    skilllen:number=0;
    operator:any=[]

    products:any=[];
    abilitys:any=[];

    scoreCode:any=[];
    productId:any;
    empl:any;

    ngOnInit() { 
      this.appraisalHdr= new AppraisalHdr();
      this.appraisalHdr.goals = [new Goal];
      this.appraisalHdr.devActions = [new DevAction];
      this.appraisalHdr.skillObjectives = [new SkillObjectives];

      this.activatedRoute.params.subscribe(params=>{          
          this.view=params.view;
          this.service.getAppraisalById(params.id).subscribe(x=>{ 
           if(x!=null){                        
            this.appraisalHdr=x;
            this.objModif(x);                                    
           }else{
          this.appraisalHdr= new AppraisalHdr();          
          this.appraisalHdr.goals = [new Goal];                    
          this.appraisalHdr.devActions = [new DevAction];
          this.objModif(x); 
        }                           
        });                         
        });
        this.service.getMetrics().subscribe(x=>{
          this.metrics=x;
        })

        this.service.getProducts().subscribe(x=>{
          this.products=x;
        })
        this.service.getOperator().subscribe(x=>{
          this.operator=x;
        })

        this.service.getScoreCode().subscribe(x=>{
          // x.forEach(element => {
          //  this.scoreCode.push(element.configDtlName+':'+element.configDtlValue)
          // });
        })

      this.service.getTimeline().subscribe(x=>{
        this.timeline=x;
      })
    }

    objModif(appraisalHdr){
      
      if(appraisalHdr.n1ReturnComment!=null && this.view=='emp' && appraisalHdr.appraisalTargetStatusCode=='PENDING-SELF-APPRAISAL'){
        this.modalReference = this.modalService.open(this.empAlert, {size: 'lg'});
      }else if(appraisalHdr.escalatReason!=null && this.view=='n2' && appraisalHdr.appraisalTargetStatusCode=='PENDING-N2-REVIEW'){
        this.modalReference = this.modalService.open(this.empAlert, {size: 'lg'});
      }
     
      for(let skill of this.appraisalHdr.skillObjectives){
        skill.dummyId=this.skilllen++
        if(skill.isMandatory=='N'){
          skill.isMandatory=null;
        }
      }

      this.appraisalHdr.goals.forEach(element => {
        element.dummyPriority=element.priority;
        if(element.isEmpAccept==null){
          element.isEmpAccept='Y'
        }
        if(element.isOrgGoal=='Y'){
          element.isOrgGoalDummy=true;
        }else{
          element.isOrgGoalDummy=false;
        }
        element.dummyid=this.len++;
      });
      this.service.getOrgGoalByEmpId(appraisalHdr.fyId).subscribe(x=>{
        this.metricList=x.orgLines;               
      });
      if(this.appraisalHdr.appraisalTargetStatusCode=='PENDING-EMP-ACCEPTANCE' || this.appraisalHdr.appraisalTargetStatusCode=='PENDING-SELF-APPRAISAL'){
        this.empView=true;
      }else{
        this.empView=false;
      } 
      console.log(this.appraisalHdr.goals);
    }

    get getFuncitional(){
      if(this.appraisalHdr.goals){
        return this.appraisalHdr.goals.filter( x => x.isTechnical == 'Y');
      }
      else
         return [];
  }

    get getBehavioral(){
      if(this.appraisalHdr.goals){
         return this.appraisalHdr.goals.filter( x => x.isTechnical == 'N');
      }
      else
         return [];
  }

  abilitySelected(id,$event){
    console.log($event);
    for(let i = 0; i < this.appraisalHdr.skillObjectives.length; ++i){
      if (this.appraisalHdr.skillObjectives[i].dummyId === id) {
          this.appraisalHdr.skillObjectives[i].abilityId=$event.item.id;
          this.appraisalHdr.skillObjectives[i].abilityName=$event.item.abilityName;
          console.log(this.appraisalHdr.skillObjectives[i]);
        }
    }
  }

  productSelected(id,$event){
    console.log($event);
    for(let i = 0; i < this.appraisalHdr.skillObjectives.length; ++i){
      if (this.appraisalHdr.skillObjectives[i].dummyId === id) {
          this.appraisalHdr.skillObjectives[i].productId=$event.item.id;
          this.appraisalHdr.skillObjectives[i].productName=$event.item.materialName;
          console.log(this.appraisalHdr.skillObjectives[i]);
        }
    }
  }  

  formService(id){
    if(this.productId!=id){
        this.productId=id;
        this.service.getSkillFromProdId(id).subscribe(x=>this.abilitys=x.materialServices);
    }     
}

  addSkillInObj(){
    let goals = new Goal();
    goals.isOrgGoalDummy=false;
    goals.isOrgGoal='N';
    this.appraisalHdr.goals
  }

  addSkill(){
    let skill = new SkillObjectives();
    skill.dummyId=this.skilllen++
    skill.appraisalHdrId=this.appraisalHdr.id.toString();
    skill.isExistingSkill='N';
    skill.currSkillLevel=0;
    skill.skillLevelFromEmp=0;
    skill.skillLevelFromN1=0;
    skill.productName=null;
    skill.abilityName=null;
    this.appraisalHdr.skillObjectives.push(skill);
  }
  
  removeSkill(id){

    for(let i = 0; i < this.appraisalHdr.skillObjectives.length; ++i){
      if (this.appraisalHdr.skillObjectives[i].dummyId === id) {
          this.appraisalHdr.skillObjectives.splice(i,1);
      }
  }

    // this.appraisalHdr.skillObjectives.splice(i,1);
  }

  get ratingTotal(){
    let total = 0;
    for(let skill of this.appraisalHdr.skillObjectives){
      if(skill.currSkillLevel!=null && skill.currSkillLevel!=0){      
        total=total+(skill.currSkillLevel);
      }
    }
    return total;
  }

  SkillGoal(id){
    for(let i = 0; i < this.appraisalHdr.goals.length; ++i){
      if(this.appraisalHdr.goals[i].dummyid==id){
      this.appraisalHdr.goals[i].isSkillGoal='Y';
      this.appraisalHdr.goals[i].metricId='skill-goal';
      this.appraisalHdr.goals[i].metricName='Skill Score';
      this.appraisalHdr.goals[i].unitDisp='Sc'
      this.appraisalHdr.goals[i].unitDesc='Sc'
      this.appraisalHdr.goals[i].empTarget=this.selfRating.toString();
      this.appraisalHdr.goals[i].n1Target=this.N1Rating.toString();
      this.appraisalHdr.goals[i].currSkillCount=this.ratingTotal;
      this.appraisalHdr.goals[i].skillScoreFromEmp=this.selfRating;
      this.appraisalHdr.goals[i].skillScoreFromN1=this.N1Rating;
      this.appraisalHdr.goals[i].empOperator='>=';
      this.appraisalHdr.goals[i].n1Operator='>=';
      }
    }
  }

  get findSkillUsed(){
    let isSkillGoal=false
    this.appraisalHdr.goals.forEach(element=>{
      if(element.metricId=='skill-goal'){
        isSkillGoal= true;
      }     
    })
    return isSkillGoal;
  }

  get selfRating(){
    let total = 0;
    for(let skill of this.appraisalHdr.skillObjectives){
      if(skill.skillLevelFromEmp!=null && skill.skillLevelFromEmp!=0){
        total=total+(skill.skillLevelFromEmp);
      }
    }
    return total;
  }

  get N1Rating(){
    let total = 0;
    for(let skill of this.appraisalHdr.skillObjectives){
      if(skill.skillLevelFromN1!=null && skill.skillLevelFromN1!=0){
        total=total+(skill.skillLevelFromN1);
      }
    }
    return total;
  }

  years:any=[{'year':'2020-03-01'},{'year':'2020-06-01'},{'year':'2020-09-01'},{'year':'2020-12-01'}]

  addChildRow(id,type){  
    for(let i = 0; i < this.appraisalHdr.goals.length; ++i){
      if(this.appraisalHdr.goals[i].dummyid==id){
    let reviews =new Review
     this.appraisalHdr.goals[i].reviews=[]    
    switch(type){
      case 'yearly':    
      reviews =new Review;    
        reviews.reviewDt='2020-03-01';  
        reviews.reviewTypeCode='yearly';
        this.appraisalHdr.goals[i].reviews.push(reviews);      
      break;
      case 'half-yearly':        
        for (let index = 0; index < 2; index++) { 
          reviews =new Review;                    
          reviews.reviewDt=this.years[index].year;
          reviews.reviewTypeCode='half-yearly';  
          this.appraisalHdr.goals[i].reviews.push(reviews); 
        }

      break;
      case 'quarterly':
        for (let index = 0; index < 4; index++) { 
          reviews =new Review;                     
          reviews.reviewDt =this.years[index].year; 
          reviews.reviewTypeCode='quarterly'; 
          this.appraisalHdr.goals[i].reviews.push(reviews); 
        }
      break;
    }  
  }
}
  }
 
  //For Sorting.....


  submitSort(aciton){
    let isFun=false;
    let isNonFun=false;    
    this.action='update';
    
    for (let index = 0; index < this.DfuncitionalSort.length; index++) { 
      if(this.DfuncitionalSort[index].dummyPriority!=index+1){
        isFun=true;               
      }
    }
    for (let index = 0; index < this.DbehavioralSort.length; index++) {     
      if(this.DbehavioralSort[index].dummyPriority!=index+1){
        isNonFun=true;             
      }
    }
    if(isFun==false&&isNonFun==false){
      this.appraisalHdr.goals.forEach(element => {
        element.priority=element.dummyPriority;
      });
      this.sortMsg = { type: 'success', text: 'Sorted' } 
      if(aciton=='submit'){
        this.update();
        this.modalReference.close();
      }     
    }else{
      this.sortMsg = { type: 'danger', text: 'pls Fill All priority & avoid duplicate' }      
    }
  }

  get behavioralSort(){
    return this.getBehavioral.sort((a,b)=>a.priority-b.priority);
  }

  get funcitionalSort(){
    return this.getFuncitional.sort((a,b)=>a.priority-b.priority);
  }

  get DbehavioralSort(){
    return this.getBehavioral.sort((a,b)=>a.dummyPriority-b.dummyPriority);
  }

  get DfuncitionalSort(){
    return this.getFuncitional.sort((a,b)=>a.dummyPriority-b.dummyPriority);
  }
  // For Sorting 
addDevActions(){
  let devActions = new DevAction
this.appraisalHdr.devActions.push(devActions);
}
removeDevActions(i){
  this.appraisalHdr.devActions.splice(i,1);
}

  metricSelected(id,$event,state){ 
    console.log($event.item)
    console.log(state)  
    for(let i = 0; i < this.appraisalHdr.goals.length; ++i){
      if(this.appraisalHdr.goals[i].dummyid==id){
        if(state){
          this.appraisalHdr.goals[i].metricId=$event.item.metricId;
          this.appraisalHdr.goals[i].empTarget=$event.item.metricValue;
          this.appraisalHdr.goals[i].empOperator=$event.item.operator;
        }
        if(!state){
          this.appraisalHdr.goals[i].metricId=$event.item.id;
          this.appraisalHdr.goals[i].empTarget=$event.item.threshold;
          this.appraisalHdr.goals[i].empOperator=$event.item.better;
        }        
        this.appraisalHdr.goals[i].metricName=$event.item.metricName;
        this.appraisalHdr.goals[i].unitDisp=$event.item.unitTypeSymbol;
        this.appraisalHdr.goals[i].unitDesc=$event.item.descp;
      }      
    }     
  }

  cancel(){
    this.ngOnInit();
  }

  back(){    
    this.route.navigateByUrl("my-task/appraisal/"+this.view);
  }

  showWarning(){
    this.modalReference.close(this.timelines);
    this.modalReference = this.modalService.open(this.showwarning);	
  }

  viewTimeline(action){
    this.action=action;
    let count = 0;

    if(action=='update'){
      this.update();
    }else{
      if(action=='emp-accept'){
        this.appraisalHdr.goals.forEach(element => {
          if(element.isEmpAccept=='N'){
            count++
          }
        });
      }
      if(count==0){
        this.modalReference = this.modalService.open(this.timelines, {size: 'lg'});	
      }else{
        this.modalReference = this.modalService.open(this.empAlert,{size:'lg'})
      } 
    }

    
  }

  priority(){
    this.modalReference = this.modalService.open(this.dragDrop, {size: 'lg'});	
  }

  addRow(type){  
           
    let goals = new Goal();
    goals.dummyid=this.len++;    
    goals.isTechnical=type;
    goals.isOrgGoalDummy=false;
    goals.strategy='functional';
    goals.reviewIntervalType='yearly'    
    this.appraisalHdr.goals.push(goals);
    this.addChildRow(goals.dummyid,goals.reviewIntervalType);
    console.log(this.appraisalHdr.goals);
}

start(){
  this.service.updateAppraisalByAction(this.appraisalHdr.id,'start',this.appraisalHdr).subscribe(x=>{
    this.ngOnInit();
    console.log(x);
  },error=>{
    this.errorMsg=JSON.parse(error._body);
    this.modalReference.close();
    this.Alertmsg = { type: 'danger', text: this.errorMsg.message }   
  })
}

update(){
  let i=1;
  let b=1;
  if(this.appraisalHdr.appraisalTargetStatusCode=='PENDING-SELF-APPRAISAL'){
  this.getFuncitional.forEach(element => {
      element.priority=i++;    
  });
  this.getBehavioral.forEach(element => {
    element.priority=b++;    
});
}

  this.appraisalHdr.goals.forEach(goal => {   
    goal.finalTarget=goal.n1Target;
    if(goal.metricId=='skill-goal'){
      this.SkillGoal(goal.dummyid);
    }
    goal.isOrgGoal = (goal.isOrgGoalDummy ? "Y" : "N");
  }); 
  this.appraisalHdr.skillObjectives.forEach(skill=>{
    skill.isMandatory = (skill.isMandatory?"Y":"N");
  })
  this.service.updateAppraisalByAction(this.appraisalHdr.id,this.action,this.appraisalHdr).subscribe(x=>{
    if(this.action!='update'){
      this.modalReference.close();
    }    
    this.Alertmsg = { type: 'success', text: 'Updated Successfully' }   
    this.ngOnInit();
    console.log(x);
  },error=>{    
    this.errorMsg=JSON.parse(error._body);
    this.modalReference.close();
    this.Alertmsg = { type: 'danger', text: this.errorMsg.message }   
  })
}

deleteRow(id,type){
  for(let i = 0; i < this.appraisalHdr.goals.length; ++i){
    if (this.appraisalHdr.goals[i].dummyid === id&&this.appraisalHdr.goals[i].isTechnical==type) {
        this.appraisalHdr.goals.splice(i,1);
    }
}
}

returnReason(action){
  this.action=action;
  this.modalReference = this.modalService.open(this.returnRes, {size: 'lg'});	
}


}