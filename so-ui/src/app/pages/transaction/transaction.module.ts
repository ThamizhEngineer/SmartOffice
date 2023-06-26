import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { TransactionRoutes } from './transaction.routing';
import { TransactionComponent } from './transaction.component';
import { SaleOrderService} from'./sale-order/sale-order.service';
import { TypeaheadPopupDirective } from './popup.directive';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(TransactionRoutes)
  ],
  providers:[SaleOrderService],
  declarations: [ TransactionComponent, TypeaheadPopupDirective ]
})
export class TransactionModule { }
