
import { Component, OnInit } from '@angular/core';
import { SaleOrderService } from '../sale-order.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SaleOrder } from '../../model/sale-order';
import { FormsModule } from '@angular/forms';
@Component({
    selector: 'sale-order-detail',
    templateUrl: './sale-order-detail.component.html'
})
export class SaleOrderDetailComponent implements OnInit {
    addScreen: boolean = true;
    saleOrder: SaleOrder;
    constructor(private router: Router,
        private route: ActivatedRoute,
        private service: SaleOrderService) {

    }


    ngOnInit() {
 
        this.saleOrder = new SaleOrder;
        // console.log(this.route.params['_value']['_id'])
        // if (this.route.params['_value']['_id']) {
        //     //route.params['value'] will have the '_id' in it, during edit 
        //     this.route.params
        //         .subscribe((_params: Params) => {
        //             this.addScreen = false;
        //             this.service.getAllSaleOrderById(_params['_id']).subscribe(so => {
        //                 this.saleOrder = so;
        //                 this.formatChangesforUI();
        //                 console.log(so);
        //             })
        //         });
        // };

        this.route.params.switchMap((_params: Params) =>  this.service.getAllSaleOrderById(_params['_id']))
        .subscribe(so => {
            this.addScreen = false;
            this.saleOrder = so;
            this.formatChangesforUI();
            console.log(so);
        });
    }

    saveSaleOrder(){
        this.formatChangesforDB();
        if(this.addScreen){
            this.addSaleOrder();
        }else{
            this.updateSaleOrder();
        }
    }
    addSaleOrder(){
  
 
        this.service.addSaleOrder(this.saleOrder).subscribe(
            result => {
				
                this.listSaleOrder();
            },
            error => {
                console.error('Error adding sale order!');
                console.error(error);
            }

        );
    }
    updateSaleOrder(){
        this.service.updateSaleOrder(this.saleOrder).subscribe(
            result => {
				
                this.listSaleOrder();
            },
            error => {
                console.error('Error updating sale order!');
                console.error(error);
            }

        );
    }


    listSaleOrder(){
        this.router.navigateByUrl("transaction/sale-order/sale-order-list")
    }

    formatChangesforUI() {
        this.saleOrder.soDt = (this.saleOrder.soDt ) ? this.saleOrder.soDt .substr(0, 10) : "";       
    }
    formatChangesforDB() {
        this.saleOrder.soDt = new Date(this.saleOrder.soDt).toISOString();     
    }
    
}


