import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { JobFeedbackRoutes } from './job-feedback.routing';
import { JobFeedbackDetailComponent } from './_comp/detail.component';
import { JobFeedbackListComponent } from './_comp/list.component';

import { JobFeedbackService } from './job-feedback.service';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,
    RouterModule.forChild(JobFeedbackRoutes),
	AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
    })
  ],
  providers:[JobFeedbackService],
  declarations: [ 
    JobFeedbackDetailComponent, JobFeedbackListComponent
  ]
})
export class JobFeedbackModule { }
