import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FormWizardModule } from 'angular2-wizard';

import { FullCalendarModule } from 'ng-fullcalendar';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PurchaseOrderService} from './purchase-order.service';
import { JobService} from '../job.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FileModule } from './../../../shared/file.module';
import { PurchaseOrderListComponent } from './_comp/purchase-order-list.component';
import { PurchaseOrderDetailComponent } from './_comp/purchase-order-detail.component';
import { PurchaseOrderViewComponent } from './_comp/purchase-order-view.component';
import { PurchaseOrderRoutes } from './purchase-order.routing';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule, FormWizardModule, FullCalendarModule, PerfectScrollbarModule,FileModule,
    RouterModule.forChild(PurchaseOrderRoutes),
  ],
  providers:[PurchaseOrderService,JobService],
  declarations: [ PurchaseOrderListComponent,PurchaseOrderDetailComponent, PurchaseOrderViewComponent]
})
export class PurchaseOrderModule { }
