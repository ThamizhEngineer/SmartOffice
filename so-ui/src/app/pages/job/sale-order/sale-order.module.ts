import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../../shared/shared.module';
import { FormsModule} from '@angular/forms';

import { SaleOrderListComponent } from '../sale-order/_comp/sale-order-list.component';
import { SaleOrderDetailComponent } from '../sale-order/_comp/sale-order-detail.component';
import {
    ReactiveFormsModule
  } from '@angular/forms';
import { SaleOrderService} from './sale-order.service';
import {BuisnessUnintService } from './buisness-unit.service';
import { ClientPurchaseOrderService} from './client-purchase-order.service';
import { PartnerService} from './partner-service';
import { CommonService } from '../../../shared/common/common.service';

import { SaleOrderRoutes } from './sale-order.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
    imports: [
        CommonModule, NgbModule, SharedModule,FormsModule, ReactiveFormsModule,
        RouterModule.forChild(SaleOrderRoutes),

        
    ],
    providers:[ SaleOrderService, CommonService,ClientPurchaseOrderService ,BuisnessUnintService,PartnerService],
    declarations: [ SaleOrderListComponent, SaleOrderDetailComponent ],

})


export class SaleOrderModule { }
