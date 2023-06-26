import { NgModule } from '@angular/core';

import { BusinessListComponent } from '../business-units/comp/business-list.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { BusinessService } from '../business-units/business-service';
import { BusinessDetailComponent, ProductModelPopupComponent, ServiceModelPopupComponent } from './comp/business-detail.component';
import { RouterModule } from '@angular/router';
import { BusinessRoutes } from './business-routing';


@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,NgbModule,SharedModule,RouterModule.forChild(BusinessRoutes)],
    exports: [],
    declarations: [BusinessListComponent,BusinessDetailComponent,ProductModelPopupComponent,ServiceModelPopupComponent],
    providers: [BusinessService],
    entryComponents: [ProductModelPopupComponent,ServiceModelPopupComponent]
})
export class BusinessModule { }
