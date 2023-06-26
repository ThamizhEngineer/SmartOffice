import { Component, OnInit } from '@angular/core';

import { SkillMatrixRequestEmp,SkillMatrixRequestHdr,
        SkillMatrixRequestSkills,SkillMatrixResult,
        SkillMatrixResultQueryObject } from '../../vo/skill-matrix';

import { SkillAnalysisService } from '../skill-analysis.service'        

import { Router,ActivatedRoute } from '@angular/router';


export const ratingCode={
    '0':'dark',
    '1':'dark',
    '2':'success',
    '3':'success'
}

@Component({
    selector: '',
    templateUrl: 'gap-analysis.component.html'
})

export class GapAnalysisComponent implements OnInit {
    constructor(
        private service: SkillAnalysisService,
        private activatedRoute: ActivatedRoute
    ) { }


    info:boolean=true;
    search:boolean=true;
    process:boolean=true;
    view:string='vertical';
    rating:any=ratingCode;
    productAndService:[SkillMatrixRequestSkills]
    analysis:[SkillMatrixResult]    

    upage :number = 1;
    upageSize :number = 5;
    deptValue:string='';

    ngOnInit() { 
        this.analysis = [new SkillMatrixResult]
        this.productAndService=[new SkillMatrixRequestSkills]
        this.activatedRoute.params.subscribe(params=>{ 
       this.service.getSkillsHdr(params.key).subscribe(x=>{
           this.productAndService=x;
           this.forHorzontalAndVertical();
       });
       this.service.getSkills(params.key).subscribe(x=>{
        this.analysis=x;
        this.forHorzontalAndVertical();
        console.log(this.analysis);
       });
        });

       
    }

    forHorzontalAndVertical(){

        this.productAndService.forEach(element => {
            element.horizontal=[new SkillMatrixResult];
            element.horizontal.splice(0,1);
            for(let test of this.analysis.filter(empGap=>empGap.productId==element.productId&&empGap.serviceId==element.serviceId)){                
                element.horizontal.push(test)
            }    
      });                                           
      
      let deptValue = '';
      this.employeeList.forEach(element => {
       
        element.vertical=[new SkillMatrixResult];
        element.vertical.splice(0,1);

        if(element.deptName!=deptValue){
            deptValue=element.deptName;
            element.isSpanNeed='Y'
        }else{
            element.isSpanNeed='N'
        }
        for(let test of this.analysis.filter(empGap=>empGap.employeeId==element.employeeId)){                
            element.vertical.push(test)
        }    
  });  
  console.log(this.productAndService)
    }


    get employeeList(){                   
        return  this.analysis.filter((item,i,array)=>this.analysis.indexOf(array.find(x=>x.employeeId===item.employeeId))===i);         
    }

    get department(){
        return  this.analysis.filter((item,i,array)=>this.analysis.indexOf(
            array.find(x=>x.deptId===item.deptId))===i);         
    }

   
}