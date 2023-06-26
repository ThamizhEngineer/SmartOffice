
import {  Component,OnInit} from '@angular/core';
import { PurchaseOrderService} from '../purchase-order.service';

@Component({
    selector:'purchase-order-list',
    templateUrl:'./purchase-order-list.component.html',
   
})
export class PurchaseOrderListComponent implements OnInit{
    rows=[];
    constructor(private _service:PurchaseOrderService){}
    ngOnInit() {
        this._service.getAllPurchaseOrders().subscribe(x=>{
            this.rows = x.content;
        })
    }
    
}