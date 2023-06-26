import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { FileModule } from './../../../shared/file.module';

import { AttendanceComponent } from './comp/attendance.component';
import { AttendanceCheckService } from './attendance.service';

const routes: Routes = [
     {path:'',component:AttendanceComponent},    
]

@NgModule({
    imports: [CommonModule,FileModule,FormsModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [AttendanceComponent],
    providers: [AttendanceCheckService],
})
export class AttendanceCheckModule { }