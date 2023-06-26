
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SaleOrderService } from '../sale-order.service';
@Component({
    selector: 'sale-order-list',
    templateUrl: './sale-order-list.component.html'
})
export class SaleOrderListComponent implements OnInit {

    saleOrders:any;
    constructor(private router:Router,
        private service:SaleOrderService){

    }

    ngOnInit() {

        this.service.getAllSaleOrders().subscribe(x=>{
            this.saleOrders = x;
            console.log(x)
        })

    
    }
    
    addSaleOrder(){

     this.router.navigateByUrl("transaction/sale-order/sale-order-new");   
    }

    editSaleOrder(_id:string){

        console.log(_id)

        this.router.navigateByUrl("transaction/sale-order/sale-order-detail/"+_id);   
       }
}


