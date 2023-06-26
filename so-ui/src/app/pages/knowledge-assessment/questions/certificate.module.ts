import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{AddRoutes} from './add.routing';
import{QuestionsAddDetailComponent} from './_comp/add-detail.component';
import{QuestionsAddListComponent} from './_comp/add-list.component';
import{CertificateRoutes} from './certificate.routing';
import{CertificateTrackerDetailComponent} from './_comp/certificate-tracker-detail.component';
import{CertificateTrackerComponent} from './_comp/certificate-tracker-list.component';
@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,
        RouterModule.forChild(CertificateRoutes),
    ],
   
    declarations: [CertificateTrackerComponent,CertificateTrackerDetailComponent]
})


export class CertificateModule { }
