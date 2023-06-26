import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { TrackJobService } from './track-jobs.service';
// import { AuthInterceptor } from './auth-Interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { TrackJobsListComponent } from '../track-jobs/_comp/track-jobs-list.component';
import { TrackJobsDetailComponent } from '../track-jobs/_comp/track-jobs-detail.component';
import { TrackJobsRoutes } from './track-jobs.routing';
import { AuthInterceptor } from '../../operation/employee/auth-Interceptor.service';

@NgModule({
    imports:[
        RouterModule.forChild(TrackJobsRoutes),
        CommonModule, FormsModule, NgbModule, SharedModule,FileModule
    ],
	providers:[ TrackJobService, {provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor, multi: true} ],
    declarations:[TrackJobsListComponent,TrackJobsDetailComponent]
})

export class TrackJobsModule{}