import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import {  ApplicantListComponent } from './_comp/applicant-list.component';
import { ApplicantDetailComponent } from './_comp/applicant-detail.component';
// import { EmployeeNewComponent } from './_comp/employee-new.component';
import { FileModule } from './../../../shared/file.module';
import { ApplicantService} from '../applicant/applicant.service';
import { ApplicantRoutes } from './applicant.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DateParserService } from '../../../shared/_services/date-parser.service';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,ReactiveFormsModule,SharedModule,FileModule,
        RouterModule.forChild(ApplicantRoutes),
    ],
    declarations: [ ApplicantDetailComponent,ApplicantListComponent],
    providers:[ApplicantService, DateParserService]
})

export class ApplicantModule { }
