
import { Routes } from '@angular/router';


import{CertificateTrackerDetailComponent} from './_comp/certificate-tracker-detail.component';
import{CertificateTrackerComponent} from './_comp/certificate-tracker-list.component';
export const CertificateRoutes: Routes = [{
    path: '',
    children: [
        { path: 'certificate-tracker-detail/:_id', component:CertificateTrackerDetailComponent },
        { path: 'certificate-tracker-list', component: CertificateTrackerComponent },
        { path: '**', redirectTo: 'certificate-tracker-list' }
    ]
}];
