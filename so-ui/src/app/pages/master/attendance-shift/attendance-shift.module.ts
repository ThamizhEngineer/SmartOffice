import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { CommonService } from '../../../shared/common/common.service';

import { AttendanceShiftComponent } from './comp/attendance-shift.component';
import { AttendanceShiftService } from './attendance-shift.service';

const routes: Routes = [
    {path:'',component:AttendanceShiftComponent}
]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,SharedModule,NgbModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [AttendanceShiftComponent],
    providers: [CommonService,AttendanceShiftService],
})
export class AttendanceShiftModule { }
