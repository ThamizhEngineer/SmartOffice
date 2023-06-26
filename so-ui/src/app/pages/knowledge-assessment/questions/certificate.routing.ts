
import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{QuestionsAddDetailComponent} from './_comp/add-detail.component';
import{QuestionsAddListComponent} from './_comp/add-list.component';
import{CertificateTrackerDetailComponent} from './_comp/certificate-tracker-detail.component';
import{CertificateTrackerComponent} from './_comp/certificate-tracker-list.component';
export const CertificateRoutes: Routes = [{
    path: '',
    children: [
        { path: 'certificate-tracker-detail', component:CertificateTrackerDetailComponent },
        { path: 'certificate-tracker-list', component: CertificateTrackerComponent },
        { path: '**', redirectTo: 'certificate-tracker-list' }
    ]
}];
