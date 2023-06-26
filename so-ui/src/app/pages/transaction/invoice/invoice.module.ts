import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { FileModule } from './../../../shared/file.module';

import { InvoiceListComponent } from './module/invoice-list.component';
import { InvoiceService } from './invoice.service';
import { InvoiceViewComponent } from './module/invoice-view.component';
import { InvoiceEditComponent } from './module/invoice-edit.component';
const routes: Routes = [
    {path:'',component:InvoiceListComponent},
    {path:'view/:id',component:InvoiceViewComponent},
    {path:'edit/:id',component:InvoiceEditComponent},
    {path:'new',component:InvoiceEditComponent}
]


@NgModule({
    imports: [CommonModule,FileModule,FormsModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [InvoiceListComponent,InvoiceViewComponent,InvoiceEditComponent],
    providers: [InvoiceService],
})
export class InvoiceModule { }
