import { Component, OnInit } from '@angular/core';
import { MySkillService } from '../../../my-space/my-skill/my-skill.service';
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Employee,EmployeeSkill} from '../../../vo/employee';

import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: '',
    templateUrl: 'my-team-skill.component.html'
})

export class MyTeamSkillComponent implements OnInit {
    searchProduct = (text$: Observable<string>) =>
    text$.pipe(
        debounceTime(200),
        distinctUntilChanged(),
        map(term => (term === '' ? this.productNames
            : this.productNames.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
productFormatter = (x: { materialName: string }) => { x.materialName};

searchSkill = (text$: Observable<string>) =>
text$.pipe(
    debounceTime(200),
    distinctUntilChanged(),
    map(term => (term === '' ? this.serviceNames
        : this.serviceNames.filter(v => v.abilityName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
);
skillFormatter = (x: { abilityName: string }) => {x.abilityName};

    serviceNames: any = [];
    productNames: any = [];
    skillCode: any=[];
    user:User;
    id:any;
    employee:Employee;
    msg:any;
    productId:any;

  
    constructor(
        private service:MySkillService,
        private userService:UserService,
        private activatedRoute:ActivatedRoute
    ) { }

    ngOnInit() { 
        this.employee = new Employee();
        this.employee.employeeSkills= [new EmployeeSkill];

        this.user = this.userService.getCurrentUser();
        this.id = this.user.employeeId;

        if (this.activatedRoute.params['_value']['id']) {            
			this.activatedRoute.params.switchMap((params: Params) => this.service.getEmployeeById(params['id']))
			.subscribe( x => {	
                this.employee=x;
                this.skill();
                console.log(this.employee);             
			});
		}
        this.service.getSkill().subscribe(x=>{
			this.skillCode=x;
        })
        
        this.service.getProducts().subscribe(x=>{
                this.productNames=x;
            })

        // this.service.getJobs('employeedetails').subscribe(x=>{
        //             this.serviceNames=x;                    
        //     });

    }
    
    skill(){
        let empSkill = new EmployeeSkill();
        if(this.employee.employeeSkills[0]==null){
            empSkill.skillLevelCode='1'
            this.employee.employeeSkills.push(empSkill);
        }
    }

    addEmployeeSkills() {	
        this.employee.employeeSkills[0]==null
    let empSkill = new EmployeeSkill();
    if(empSkill.skillLevelCode==null){
        empSkill.skillLevelCode='1'
    }
    this.employee.employeeSkills.push(empSkill); 
}
delEmployeeSkills(i) {
    this.employee.employeeSkills.splice(i, 1);
}

formService(id){
    if(this.productId!=id){
        this.productId=id;
        this.service.getSkillFromProdId(id).subscribe(x=>this.serviceNames=x.materialServices);
    }     
}

saveSkill(){

    console.log(this.employee);
		this.service.EmployeeUpdate(this.employee.id,'skill-validate',this.employee).subscribe(x => {
                this.msg = { type: 'success', text: "Skill Update successfully" }
                this.ngOnInit();
		}, error => {
				this.msg = { type: 'danger', text: "Error in Updation" }			
		});
}

onProductSelect($event, item) {
    console.log($event)
    this.employee.employeeSkills[item].mproductId = $event.item.id
    this.employee.employeeSkills[item].productName = $event.item.materialName
}

onSkillSelect($event, item){
    console.log($event)
    let productId = this.employee.employeeSkills[item].mproductId;
    let serviceId = $event.item.id;
    console.log(productId)
    console.log(serviceId)
    console.log(this.employee.employeeSkills)
    var response = this.employee.employeeSkills
    .filter((employeeSkill: EmployeeSkill ) => employeeSkill.mproductId === productId && employeeSkill.mprofileId===serviceId)
    console.log(response)
    if(response.length!=0){
        this.msg = { type: 'danger', text: "Duplicate record found" }
        return false    
    }else{     
    this.employee.employeeSkills[item].mprofileId = $event.item.id
    this.employee.employeeSkills[item].serviceName = $event.item.abilityName
}
}
}