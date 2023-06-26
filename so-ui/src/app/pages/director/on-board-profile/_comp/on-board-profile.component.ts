
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {EmployeeService} from '../../../operation/employee/employee.service';
import { Employee } from '../../../vo/employee';
import { status_css } from '../../../vo/status'

@Component({
    selector: '',
    templateUrl: './on-board-profile.component.html',
  
})
export class OnBoardProfileComponent implements OnInit {

    employee:Employee;
    active:any=[];
    inactive:any=[];
    binding:any='true';
	statuses:any=status_css;
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
                if(list.empStatus==null || list.empStatus=='ACTIVE' ){
                    list.empStatus='ACTIVE';
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
        this.router.navigate(['/operation/employee/detail/'] ,{queryParams:{flowType:"On Board Profile",id:_id}});
    }
}
