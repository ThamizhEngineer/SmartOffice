import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule,ReactiveFormsModule }   from '@angular/forms';
import { InterviewTrackerListComponent } from './comp/interview-tracker-list.component';
import { InterviewTrackerViewComponent } from './comp/interview-tracker-view.component';
import { InterviewTrackerService } from './interview-tracker.service';
import { AngularDateTimePickerModule } from 'angular2-datetimepicker';
import { InterviewTrackerHrViewComponent } from './comp/interview-tracker-hr-view.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { ApplicantService } from '../../operation/applicant/applicant.service';
import { InterviewRoundListComponent } from './comp/interview-round-list.component'; 
import { InterviewConductComponent } from './comp/interview-conduct.component';




const routes: Routes = [
    {path:'',component:InterviewRoundListComponent},
    {path:'view/:id',component:InterviewTrackerViewComponent},
    {path:'hr-view/:id',component:InterviewTrackerHrViewComponent},
    {path:'conduct-interview/:id',component:InterviewConductComponent} 
    
]

@NgModule({
    imports: [CommonModule,FileModule,NgxPaginationModule,SharedModule,ReactiveFormsModule,AngularDateTimePickerModule,NgbModule,FormsModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [InterviewTrackerListComponent,InterviewTrackerViewComponent,InterviewTrackerHrViewComponent, InterviewRoundListComponent, InterviewConductComponent],
    providers:[InterviewTrackerService,ApplicantService]
})
export class InterviewTrackerModule { }