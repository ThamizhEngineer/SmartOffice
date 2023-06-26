
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {EmployeeService} from '../../employee/employee.service';
import { Employee } from '../../../vo/employee';
import { status_css } from '../../../vo/status'

@Component({
    selector: 'employee-list',
    templateUrl: './employee-list.component.html',
  
})
export class EmployeeListComponent implements OnInit {

    employee:Employee;
    statuses:any=status_css;
    active:any=[];
    inactive:any=[];
    binding:any='true';
    rows:Array<Employee>;
    constructor(private router:Router,private service:EmployeeService){

    }

    ngOnInit() {
    this.employee = new Employee();
    this.rows = new Array<Employee>();
    this.getEmployee();

    }

    getEmployee(){
        this.service.getEmployees().subscribe(_emps=>{
            for(let list of _emps){
                if(list.empStatus=='ACTIVE' || list.empStatus==null){
                    list.empStatus='ACTIVE';
                    this.active.push(list);
                }else{
                    this.inactive.push(list);
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
        this.router.navigate(['/operation/employee/detail/'] ,{queryParams:{flowType:"Edit Employee",id:_id}});
    //  this.router.navigateByUrl("/operation/employee/detail/"+id);   
    }
}
