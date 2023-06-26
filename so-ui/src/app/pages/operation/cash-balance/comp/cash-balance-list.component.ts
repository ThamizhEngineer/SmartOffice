import { Observable } from 'rxjs/Rx';
import { CashBalanceService } from './../cash-balance.service';
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CashBalance } from './../../../vo/cash-balance';

@Component({
selector: 'cash-balance-list',
templateUrl: './cash-balance-list.component.html',
})
export class CashBalanceComponent implements OnInit {
employee:Array<CashBalance>
saveMsg:any;
constructor(private cashbalanceService:CashBalanceService,private router:Router){}
ngOnInit():void {
this.getcashbalance();
}
getcashbalance(): void {
this.cashbalanceService.getcashbalance().subscribe(data =>{
this.employee= data
});
}
deleteRow(id?: any){
        this.cashbalanceService.deletecashbalance(id).subscribe(x=>{
// console.log(x);
this.saveMsg = {'type': 'success', 'text': "Cashbalance Deleted Successfully"}
this.getcashbalance(); 
},error=>{
this.saveMsg = {'type': 'danger', 'text': "Server Error"};
});
}

navigateToDetail(id){
        this.router.navigateByUrl("/operation/cash-balance/detail/"+id);
}

}

