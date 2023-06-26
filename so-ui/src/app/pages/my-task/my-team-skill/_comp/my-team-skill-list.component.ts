
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {EmployeeService} from '../../../operation/employee/employee.service';
import { Employee } from '../../../vo/employee';
import { status_css } from '../../../vo/status'

@Component({
    selector: '',
    templateUrl: './my-team-skill-list.component.html',
  
})
export class MyTeamSkillListComponent implements OnInit {

    employee:Employee;
    rows:Array<Employee>;
    active:any=[];
    inactive:any=[];
    binding:any='true';
    statuses:any=status_css;

    page :number = 1;    
    pageSize :number = 10
    
    constructor(private router:Router,private service:EmployeeService){

    }

    ngOnInit() {
    this.employee = new Employee();
    this.rows = new Array<Employee>();
    this.getEmployee();

    }

    getEmployee(){
        this.service.getN1EmployeeSkill().subscribe(_emps=>{
            console.log(_emps);
            for(let list of _emps.memployeeList){
                if(list.empStatus!='SKILL-VALIDATION-PENDING'){                    
                    this.active.push(list);
                }else{
                    this.inactive.push(list);
                }
            }
            this.rows =_emps;
        })
    }

    deleteRow(id:any){
        this.service.deleteEmployee(id).subscribe(x=>{
            console.log(x);
           
            this.getEmployee();
        },error=>{
           
        });
    }

    changeView(value){
        this.binding=value;
    }
    
    navigateToDetailPage(_id:number){
        this.router.navigate(['/my-task/my-team-skill/view/',_id]);
    }
}
