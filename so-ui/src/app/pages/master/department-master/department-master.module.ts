import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { DepartmentMasterComponent } from './comp/department-master.component';
import { DepartmentMasterRoutes } from './department-master.routing';
import { DepartmentMasterService } from './department-master.service';
// import { DepartmentMasterDetailComponent } from './comp/department-master-detail.component';

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,SharedModule,NgbModule,RouterModule.forChild(DepartmentMasterRoutes)],
    exports: [],
    declarations: [DepartmentMasterComponent],
    providers: [DepartmentMasterService],
})
export class DepartmentMasterModule { }
