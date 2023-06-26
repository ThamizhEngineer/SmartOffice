import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../shared/shared.module';
import { AttendanceRoutes } from './attendance.routing';

@NgModule({
    imports: [CommonModule, FormsModule, NgbModule,SharedModule,
        RouterModule.forChild(AttendanceRoutes)],
    exports: [],
    declarations: [],
    providers: [],
})
export class AttendanceModule { }
