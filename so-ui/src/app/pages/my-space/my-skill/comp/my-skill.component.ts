import { Component, OnInit } from '@angular/core';
import { MySkillService } from '../my-skill.service';
import { UserService } from '../../../../auth/_services/user.service';
import { User } from '../../../../auth/_models/user';
import { Employee, EmployeeSkill } from '../../../vo/employee';

import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: '',
    templateUrl: 'my-skill.component.html'
})

export class MySkillComponent implements OnInit {

    searchProduct = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.productNames
                : this.productNames.filter(v => v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    productFormatter = (x: { materialName: string }) => {
        x.materialName
    };

    searchSkill = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.serviceNames
                : this.serviceNames.filter(v => v.abilityName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    skillFormatter = (x: { abilityName: string }) => {
        x.abilityName
    };

    serviceNames: any = [];
    productNames: any = [];
    skillCode: any = [];
    user: User;
    id: any;
    employee: Employee;
    msg: any;
    productId:any;
    employeeFIlteredList: any=[];
    mproductId: number;
    pageSize :number = 10;
	page :number = 1;


    constructor(
        private service: MySkillService,
        private userService: UserService
    ) { }

    ngOnInit() {
        this.employee = new Employee();
        this.employee.employeeSkills = [new EmployeeSkill];

        this.user = this.userService.getCurrentUser();
        this.id = this.user.employeeId;

        this.fetchForCombos()

        this.service.getEmployeeById(this.id).subscribe(emp => {
            this.employee = emp;
            console.log(this.employee)
            if (this.employee.employeeSkills[0] == null) {
                this.addEmployeeSkills();
            }
        })

    }

    onProductSelect($event, empSkill:EmployeeSkill) {              
        empSkill.mproductId = $event.item.id
        empSkill.productName = $event.item.materialName
        this.mproductId = $event.item.id
    }

    onSkillSelect($event, empSkill:EmployeeSkill){
        let productId = empSkill.mproductId
        let serviceId = Number($event.item.id)
        var response = this.employee.employeeSkills
         .filter((employeeSkill: EmployeeSkill ) => employeeSkill.mproductId === productId && employeeSkill.mprofileId===serviceId)
         if(response.length!=0){
        this.msg = { type: 'danger', text: "Duplicate record found" }
        }else{
            empSkill.mprofileId = Number($event.item.id)
            empSkill.serviceName = $event.item.abilityName
         }
        }


    fetchForCombos() {
        this.service.getSkill().subscribe(x => {
            this.skillCode = x;
        })
        this.service.getProducts().subscribe(x => {
            this.productNames = x;
        })
        // this.service.getJobs('employeedetails').subscribe(x => {
        //     this.serviceNames = x;
        // });
    }

    formService(id){
        if(this.productId!=id){
            this.productId=id;
            this.service.getSkillFromProdId(id).subscribe(x=>this.serviceNames=x.materialServices);
        }     
    }

    addEmployeeSkills() {
        let empSkill = new EmployeeSkill();
        if (empSkill.skillLevelCode == null) {
            empSkill.skillLevelCode = '1'
            empSkill.productName = ""
            empSkill.serviceName = ""
        }
        this.employee.employeeSkills.push(empSkill);
        console.log(empSkill)
    }

    delEmployeeSkills(i) {
        let pgNo= this.page
        let arrayNo = (pgNo-1)*this.pageSize;      
        this.employee.employeeSkills.splice((arrayNo+i), 1);
    }

    saveSkill() {
    this.service.updateEmployeeSkill(this.employee, this.employee.id).subscribe(x => {
        this.msg = { type: 'success', text: "Skill Update Submitted successfully" }
        this.ngOnInit();
    }, error => {
        this.msg = { type: 'danger', text: "Error in Updation" }
    });
}
}
