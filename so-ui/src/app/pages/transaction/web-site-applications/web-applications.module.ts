import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WebApplicationsService } from './web-applications.service';
import { WebApplicationsComponent } from './comp/web-applications.component';
import { ValidateApplicationsComponent } from './comp/validate-applications.component';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
import { ToggleButtonComponent } from './toggle/toggle-button.component';
import { ApplicantService } from '../../operation/applicant/applicant.service';

const routes: Routes = [
    { path: 'list', component: WebApplicationsComponent },
    { path: '', component: ValidateApplicationsComponent }
]

@NgModule({
    imports: [CommonModule, FormsModule, NgbModule, SharedModule, FileModule, RouterModule.forChild(routes)],
    exports: [],
    declarations: [WebApplicationsComponent, ValidateApplicationsComponent, ToggleButtonComponent],
    providers: [WebApplicationsService,ApplicantService]
})
export class WebApplicationModule { }