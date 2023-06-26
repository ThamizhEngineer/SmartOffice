import { Routes } from '@angular/router';


import {TravelApprovalComponent} from '../travel/_comp/travel-approval.component'
import { TravelDetailComponent} from '../travel/_comp/travel-detail.component'
import { TravelRequestComponent} from '../travel/_comp/travel-request.component'

export const TravelRoutes: Routes = [{
    path: '',
    children: [
        // { path: 'application-list', component: ApplicationDetailComponent,  pathMatch: 'full' },
        { path: 'travel-request', component: TravelRequestComponent },
        { path: '**', redirectTo: 'travel-request' }
    ]
}];
