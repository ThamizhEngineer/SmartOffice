
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {EmployeeConventionService} from '../employee-convention.service';
import { Employee } from '../../../vo/employee';
import { status_css } from '../../../vo/status'

@Component({
    selector: '',
    templateUrl: './employee-convention-list.component.html',
  
})
export class EmployeeConventionListComponent implements OnInit {

    employee:Employee;
    statuses:any=status_css;
    active:any=[];
    inactive:any=[];
    binding:any='true';
    rows:Array<Employee>;
    constructor(private router:Router,private service:EmployeeConventionService){

    }

    ngOnInit() {
    this.employee = new Employee();
    this.rows = new Array<Employee>();
    this.getEmployee();

    }

    getEmployee(){
        this.service.hr1GetEmployee().subscribe(_emps=>{
            console.log(_emps);
            for(let list of _emps.memployeeList){
                if(list.empStatus=='official-information-pending'){                    
                    this.active.push(list);
                }
            }
            
            console.log(this.rows);
        })
    }

    deleteRow(id:any){
        this.service.deleteEmployee(id).subscribe(x=>{
            console.log(x);
           this.active=[];
            this.inactive=[];
            this.getEmployee();
        },error=>{
           
        });
    }
    
    changeView(value){
        this.binding=value;
    }

    navigateToDetailPage(_id:number){
        this.router.navigate(['/operation/employee-convention/detail/'] ,{queryParams:{flowType:"Edit Employee",id:_id}});
    }
}
