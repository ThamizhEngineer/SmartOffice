import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { BrowserModule } from '@angular/platform-browser';


import { AngularDateTimePickerModule } from 'angular2-datetimepicker';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

import { DtdiffPipe } from './_model/datediff';
import { DatePipe } from '@angular/common';


import { IncidentComponent } from './comp/incident.component';
import { CreateIncideintComponent } from './comp/create-incident.component';
import { AddApplicantComponent } from './comp/add-applicant.component';
import { IncidentService } from './incident.service';
import { ShortListComponent } from './comp/short-list.component';
import { ScheduleInterivewComponent } from './comp/schedule-interview.component';
import { ImportApplicantComponent } from './comp/import-applicant.component';
import { TestEligibilityComponent } from './comp/test-eligibility.component';
import {TestScheduleComponent} from './comp/test-schedule.component';
import { TestTemplateService } from '../../../pages/knowledge-assessment/test-template/test-template.service';
import { TestReportComponent,NgbdSortableHeader } from './comp/test-report.component';
import { EmployeeConversionComponent } from './comp/employee-conversion.component';
import { TrainingProgressComponent } from './comp/training-progress.component';
import { ApplicantService } from '../../operation/applicant/applicant.service';


const routes: Routes = [
    {path:':view',component:IncidentComponent},
    {path:':view/add-applicant/:id',component:AddApplicantComponent},
    {path:':view/short-list/:id',component:ShortListComponent},
    {path:':view/schedule-interview/:id',component:ScheduleInterivewComponent},
    {path:':view/new-incident',component:CreateIncideintComponent },
    {path:':view/import-applicant/:id',component:ImportApplicantComponent },
    {path:':view/test-schedule/:id',component:TestScheduleComponent},
    {path:':view/test-eligibility/:id',component:TestEligibilityComponent},
    {path:':view/edit/:view/:id',component:CreateIncideintComponent},
    {path:':view/test-report/:id',component:TestReportComponent},
    {path:':view/employee-conversion/:id',component:EmployeeConversionComponent},
    {path:':view/training-progress/:id',component:TrainingProgressComponent}



]

@NgModule({
    imports: [CommonModule,NgMultiSelectDropDownModule,AngularDateTimePickerModule,NgxPaginationModule,CommonModule,FileModule,FormsModule,ReactiveFormsModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [TestReportComponent,TestScheduleComponent],
    declarations: [IncidentComponent,TestEligibilityComponent,ScheduleInterivewComponent,TestScheduleComponent,TestReportComponent,NgbdSortableHeader,
                   CreateIncideintComponent,ImportApplicantComponent,AddApplicantComponent,ShortListComponent,DtdiffPipe,TestScheduleComponent,EmployeeConversionComponent,TrainingProgressComponent],
    providers:[IncidentService,DatePipe,TestTemplateService,ApplicantService],
    
    bootstrap: [TestReportComponent,TestScheduleComponent]

})
export class IncidentModule { }