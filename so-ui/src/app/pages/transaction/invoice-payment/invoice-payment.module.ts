import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { FileModule } from './../../../shared/file.module';

import { InvoicePaymentService } from './invoice-payment.service';
import { InvoicePaymentListComponent } from './comp/invoice-payment-list.component';
import { InvoicePaymentDetailComponent } from './comp/invoice-payment-detail.component';
const routes: Routes = [
    {path:'',component:InvoicePaymentListComponent},
    {path:'view/:id',component:InvoicePaymentDetailComponent},
    {path:'new',component:InvoicePaymentDetailComponent},
]

@NgModule({
    imports: [CommonModule,FileModule,FormsModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [ InvoicePaymentListComponent,InvoicePaymentDetailComponent ],
    providers: [ InvoicePaymentService ],
})
export class InvoicePaymentModule { }
