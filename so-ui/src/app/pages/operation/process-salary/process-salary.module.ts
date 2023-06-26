import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { SharedModule } from './../../../shared/shared.module';

import { ProcessSalaryListComponent } from './_comp/process-salary-list.component';
import { ProcessSalaryRoutes } from './process-salary.routing';
import { ProcessSalaryService } from './process-salary.service';
import { PayoutProcessService } from '../payout-process/payout-process.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,SharedModule, FullCalendarModule,
        RouterModule.forChild(ProcessSalaryRoutes),
		AgmCoreModule.forRoot({
		  apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
		}),
    ],
	providers: [ ProcessSalaryService ,PayoutProcessService],
    declarations: [ ProcessSalaryListComponent ]
})

export class ProcessSalaryModule { }