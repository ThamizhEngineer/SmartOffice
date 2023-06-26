import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeeService} from '../employee/employee.service';
import { CommonModule } from '@angular/common';
import { EmployeeListComponent } from './comp/employee-list.component';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


const routes: Routes = [
    { path: '', component: EmployeeListComponent }
]

@NgModule({
    imports: [NgbModule,CommonModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [EmployeeListComponent],
    providers:[EmployeeService]
})
export class AdminProfileModule { }