import { NgModule } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FileModule } from '../../../shared/file.module';
import { Routes, RouterModule } from '@angular/router';

import { ShiftChangeListComponent } from './comp/shift-change-list.component';
import { ShiftChangeService } from './shift-change.service';
const routes: Routes = [
  
    {path:":view",component:ShiftChangeListComponent}
]

@NgModule({
    imports: [ CommonModule,FormsModule,ReactiveFormsModule,FileModule,NgbModule,SharedModule,RouterModule.forChild(routes) ],
    exports: [],
    declarations: [ShiftChangeListComponent],
    providers: [ ShiftChangeService ],
})
export class ShiftChangeModule { }
