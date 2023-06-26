import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './_comp/customer-profile.component';
import { CustomerProfileEditComponent } from './_comp/customer-profile-edit.component';
import { CustomerRoutes } from './customer.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CustomerListComponent } from './_comp/customer-list.component';
@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(CustomerRoutes),
    ],
    declarations: [CustomerListComponent,CustomerProfileComponent,CustomerProfileEditComponent]
})

export class CustomerModule { }