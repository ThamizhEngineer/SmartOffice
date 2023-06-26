import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms'; 
//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import { SaleOrderListComponent } from '../sale-order/_comp/sale-order-list.component';
import { SaleOrderDetailComponent } from '../sale-order/_comp/sale-order-detail.component';
import { SaleOrderRoutes } from './sale-order.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,
        RouterModule.forChild(SaleOrderRoutes),
    ],
    declarations: [SaleOrderListComponent,SaleOrderDetailComponent]
})

export class SaleOrderModule { }
