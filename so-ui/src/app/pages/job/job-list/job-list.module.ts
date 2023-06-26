import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { JobListComponent } from './_comp/list.component';
import { JobListRoutes } from './job-list.routing';
import { JobService } from '../job.service';
import { CommonService } from '../../../shared/common/common.service';
import { CreateJobModelComponent } from './_comp/create-job-model.component';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule, ReactiveFormsModule, SharedModule,
        RouterModule.forChild(JobListRoutes)
    ],
    exports: [],
    declarations: [JobListComponent, CreateJobModelComponent],
    providers: [JobService, CommonService],
    entryComponents: [CreateJobModelComponent]
})
export class JobListModule { }
