import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';

import { AttendanceShowComponent } from './_comp/show.component';
import { AttendanceProxyComponent } from './_comp/proxy.component';
//import { AttendanceDirectShowComponent } from './_comp/direct.component';
import { AttendanceRoutes } from './attendance.routing';
import { AttendanceService } from './attendance.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule, FullCalendarModule, PerfectScrollbarModule,
        RouterModule.forChild(AttendanceRoutes),
		AgmCoreModule.forRoot({
		  apiKey: 'AIzaSyD2wEQkJGt5jltx8SPNjlQRpk0S1tc7LMQ'
		}),
    ],
	providers: [ AttendanceService ],
    declarations: [ AttendanceShowComponent, AttendanceProxyComponent ]
})

export class AttendanceModule { }