import { Component,OnInit } from '@angular/core';
import { SearchService } from './../../../../shared/_services/search.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Contractor } from '../../../vo/contractor';
import { ContractorService } from '../contractor.service';
@Component({
    selector: 'contractor-list',
    templateUrl: './contractor-list.component.html'
})
export class ContractorListComponent implements OnInit {
    employee:Contractor;
    rows:Array<Contractor>;
    page :number = 1;
    pageSize :number = 10;
    constructor(private router:Router,private service:ContractorService){

    }

    ngOnInit() {
    this.employee = new Contractor();
    this.rows = new Array<Contractor>();
   this.getContractor();

    }

    getContractor(){
        this.service.getContractors().subscribe(_emps=>{
            this.rows =_emps;
        })
    }

    deleteRow(id:any){
        this.service.deleteContractor(id).subscribe(x=>{
            console.log(x);
           
            this.getContractor();
        },error=>{
           
        });
    }

    navigateToDetailPage(_id:number){
        console.log(_id);
        this.router.navigate(['/operation/contractor/contractor-detail'] ,{queryParams:{flowType:"Edit Contractor",id:_id}});
    //  this.router.navigateByUrl("/operation/employee/detail/"+id);   
    }
  
}