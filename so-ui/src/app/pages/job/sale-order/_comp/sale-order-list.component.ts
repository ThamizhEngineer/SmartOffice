import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { SaleOrderService } from './../sale-order.service';

@Component({
    selector: 'sale-order-list',
    templateUrl: './sale-order-list.component.html'
})
export class SaleOrderListComponent implements OnInit {

    constructor(private router:Router, private _service: SaleOrderService){

    }

    rows: any;
    saveMsg: any;

    ngOnInit() {
        this.getSO();    	
    }

    getSO(){
        this._service.getSaleOrders().subscribe(x=>{
            this.rows = x;
        });
    }

    deleteSaleOrder(id: any){
        console.log(id);
        this._service.deleteSaleOrder(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Sale Order Deleted Successfully"};
            this.getSO();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
    }
}


