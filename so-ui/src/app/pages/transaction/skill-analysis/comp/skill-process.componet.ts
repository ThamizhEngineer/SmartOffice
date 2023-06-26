import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router,ActivatedRoute } from '@angular/router';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

import { SkillAnalysisService } from '../skill-analysis.service';
import { SkillMatrixRequestEmp,SkillMatrixRequestSkills } from '../../vo/skill-matrix';

@Component({
    selector: '',
    templateUrl: 'skill-process.componet.html'
})

export class SkillProcessComponent implements OnInit {

    searchProduct = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => (term === '' ? this.productNames : this.productNames.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
    productFormatter = (x: { materialName: string }) => { x.materialName };

    searchSkill = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => (term === '' ? this.serviceNames : this.serviceNames.filter(v => v.abilityName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
    skillFormatter = (x: { abilityName: string }) => { x.abilityName };


    serviceNames: any = [];
    productNames: any = [];
    productId:any;
    type:any;
    search:boolean=true;
    isType:boolean;
    empList:[SkillMatrixRequestEmp]
    prodAndService:[SkillMatrixRequestSkills]

    upage :number = 1;
    upageSize :number = 5;
    mpage :number = 1;
    mpageSize :number = 5;

    constructor(
        private service:SkillAnalysisService,
        private route:Router,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit() {
        this.prodAndService =[new SkillMatrixRequestSkills];
        this.empList = [new SkillMatrixRequestEmp];

        this.activatedRoute.params.subscribe(params=>{ 
            if(params.type=='gap-analysis'){
                this.type=params.type;
                this.isType=true
            }else  if(params.type=='eng-assesment'){
                this.type=params.type;
                this.isType=false
            }
        
        });

        this.service.getProducts().subscribe(x=>this.productNames=x);
     }


    formService(id){
        if(this.productId!=id){
            this.productId=id;
            this.service.getSkillFromProdId(id).subscribe(x=>{
                this.serviceNames=x.materialServices
            console.log(this.serviceNames);
            });
        }     
    }

    projectSelected($event,reqEmp:SkillMatrixRequestEmp){
        reqEmp.employeeId=$event.id;
        reqEmp.employeeName=$event.empName;
        reqEmp.departmentId=$event.deptId;
        reqEmp.deptName=$event.deptName;
    }

    addRes(){
        let emp = new SkillMatrixRequestEmp
        emp.deptName=''
        emp.employeeName=''
        this.empList.push(emp);
    }

    onProductSelect($event,prod:SkillMatrixRequestSkills){
        prod.productId=$event.item.id
        prod.productName=$event.item.materialName
    }

    onSkillSelect($event,prod:SkillMatrixRequestSkills){
       
        prod.serviceId = $event.item.id;
        prod.serviceName = $event.item.abilityName
    }

    addSkill(){
        let skill = new SkillMatrixRequestSkills()
        skill.productName=''
        skill.serviceName=''
        this.prodAndService.push(skill);
    }

    processInit(){
        let initProcess ={"processType":this.type,
        "productAndServices" :this.prodAndService,
        "employeeIds":this.empList}
        console.log(initProcess);
        this.service.initSkill(initProcess).subscribe(x=>{
            this.route.navigateByUrl('/transaction/skill-analysis');  
        })
    }

}