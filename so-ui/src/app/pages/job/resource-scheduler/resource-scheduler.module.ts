import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';

import { ResourceSchedulerListComponent } from './_comp/resource-scheduler-list.component';
import { ResourceSchedulerDetailComponent } from './_comp/resource-scheduler-detail.component';

import { ResourceSchedulerRoutes } from './resource-scheduler.routing';
import { ResourceSchedulerService } from './resource-scheduler.service';
@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,FullCalendarModule,SharedModule,
    RouterModule.forChild(ResourceSchedulerRoutes),
	AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
    })
  ],
  providers:[ResourceSchedulerService],
  declarations: [ 
    ResourceSchedulerDetailComponent, ResourceSchedulerListComponent
  ]
})
export class ResourceSchedulerModule { }
