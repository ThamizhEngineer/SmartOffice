import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { SharedModule } from './../../../shared/shared.module';

import { FullCalendarModule } from 'ng-fullcalendar';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { JobPlanListComponent } from './_comp/list.component';
import { JobPlanDetailComponent } from './_comp/detail.component';

import { JobService } from './../job.service';
import { JobPlanRoutes } from './job-plan.routing';
import {  MapLocationModule} from '../../../shared/map-location.module';
import { DatePipe } from '@angular/common';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule, FullCalendarModule, PerfectScrollbarModule, SharedModule,MapLocationModule,
    RouterModule.forChild(JobPlanRoutes),
  ],
  providers:[JobService,DatePipe],
  declarations: [ JobPlanListComponent, JobPlanDetailComponent ]
})
export class JobPlanModule { }
