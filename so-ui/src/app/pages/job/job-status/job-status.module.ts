import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { JobStatusListComponent } from './_comp/list.component';
// import { JobStatusDetailComponent } from './_comp/detail.component';

import { JobService } from './../job.service';
import { JobStatusRoutes } from './job-status.routing';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,
    RouterModule.forChild(JobStatusRoutes),
  ],
  providers:[JobService],
  declarations: [ JobStatusListComponent ]
})
export class JobStatusModule { }
