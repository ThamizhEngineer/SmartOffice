import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PayslipsService } from '../../operation/payslips/payslips.service';
// import { VisitReportRoutes } from './visit-report.routing';
import { JobReportListComponent } from './_comp/job-report-list.component';
import { JobReportDetailComponent } from './_comp/job-report-detail.component';
import{JobReportRoutes} from './job-report.routing';
import { JobService } from './../job.service';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,SharedModule,FileModule,
    RouterModule.forChild(JobReportRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
    })
  ],
  providers:[JobService,PayslipsService],
  declarations: [ 
    JobReportListComponent,JobReportDetailComponent
  ]
})
export class JobReportModule { }
