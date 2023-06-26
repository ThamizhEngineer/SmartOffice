import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { SharedModule } from './../../../shared/shared.module';

import { PayoutProcessDetailComponent } from './_comp/payout-process-detail.component';
import { PayoutProcessListComponent } from './_comp/payout-process-list.component';
import { PayoutProcessRoutes } from './payout-process.routing';
import { PayoutProcessService } from './payout-process.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,SharedModule, FullCalendarModule,
        RouterModule.forChild(PayoutProcessRoutes),
		AgmCoreModule.forRoot({
		  apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
		}),
    ],
	providers: [ PayoutProcessService ],
    declarations: [ PayoutProcessListComponent, PayoutProcessDetailComponent ]
})

export class PayoutProcessModule { }