import { NgForm } from '@angular/forms';
import { CashBalance } from './../../../vo/cash-balance';
import { CashBalanceService } from './../cash-balance.service';
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
selector: 'cash-balance-detail',
templateUrl: './cash-balance-detail.component.html',
})
export class CashBalanceDetailComponent implements OnInit {
msg:any;
    cashbalance:CashBalance;
    valu:number;  
    employee:any=[];  
constructor(private _service:CashBalanceService,
        private router:Router,
        private route:ActivatedRoute) {this.valu = +route.snapshot.params['id'];
        
    }
        
    ngOnInit(){ 
     this.cashbalance=new CashBalance;
     this.createnew();

     this._service.getEmployees().subscribe(emp=>{
         this.employee=emp;
     })

    }
    listcashbalance(){
        this.router.navigateByUrl("/operation/cash-balance/list");
    }
    cancel(){
        this.cashbalance=new CashBalance;   
    }

   createnew() {
         if(this.valu!=null){
         
         }
            this._service.getcashbalanceById(this.valu).subscribe(x=> {this.cashbalance=x
            });
}

    savecashbalance(){
            this._service.addcashbalance(this.cashbalance).subscribe(y=>{
                this.msg = { type: 'success', text: "Save successfully" }
                this.router.navigateByUrl("/operation/cash-balance/list");  
            },error=>{
                this.msg = {'type': 'danger', 'text': "Server Error"};
            });
        }

        employeeIdSelected($event){
            console.log($event);
            this.cashbalance.empName = $event.empName;
            this.cashbalance.employeeId = $event.id;
        }
}