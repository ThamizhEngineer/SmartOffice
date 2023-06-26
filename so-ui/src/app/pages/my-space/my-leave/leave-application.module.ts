import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule} from '@angular/forms';

import { SharedModule } from './../../../shared/shared.module';

import { LeaveApplicationShowComponent } from './_comp/show.component';
import { LeaveApplicationCreateComponent } from './_comp/create.component';

import { LeaveApplicationRoutes } from './leave-application.routing';
import { LeaveApplicationService } from './leave-application.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { DatediffPipe } from './_model/datediff';
import { DatePipe } from '@angular/common';
import { NgxPaginationModule } from 'ngx-pagination';
@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule, SharedModule,ReactiveFormsModule,NgxPaginationModule,
        RouterModule.forChild(LeaveApplicationRoutes),
    ],
	providers: [ LeaveApplicationService,DatePipe ],
    declarations: [ LeaveApplicationShowComponent, LeaveApplicationCreateComponent, DatediffPipe ]
})

export class LeaveApplicationModule { }
