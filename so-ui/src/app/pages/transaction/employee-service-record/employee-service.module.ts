import { NgModule } from '@angular/core';

import { EmployeeServiceComponent } from './comp/employee-service.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeServiceService } from './employee-service.service';
import { EmployeeServiceRoutes } from './employee-service.routing';


// const routes: Routes = [
//     {path:'',component:EmployeeServiceComponent}
// ]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,NgbModule,SharedModule,RouterModule.forChild(EmployeeServiceRoutes)],
    exports: [],
    declarations: [EmployeeServiceComponent],
    providers: [EmployeeServiceService],
})
export class EmployeeServiceModule { }
