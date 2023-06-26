import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FileModule } from '../../../shared/file.module';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { EmployeeLocationComponent } from './comp/emp-location.component'
import { EmployeeLocationService } from './emp-location.service';
const routes: Routes = [
    {path:'',component:EmployeeLocationComponent}
]

@NgModule({
    imports: [CommonModule,NgbModule,FormsModule,FileModule,SharedModule,ReactiveFormsModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [ EmployeeLocationComponent],
    providers:[EmployeeLocationService]
})
export class EmployeeLocationModule { }