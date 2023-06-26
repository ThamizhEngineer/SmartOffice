import { Routes } from '@angular/router';

import { TrackJobsListComponent } from '../track-jobs/_comp/track-jobs-list.component';
import { TrackJobsDetailComponent } from '../track-jobs/_comp/track-jobs-detail.component';

export const TrackJobsRoutes: Routes=[{
    path:'',
    children: [
        { path: 'detail/:_id', component: TrackJobsDetailComponent },
        { path: 'list', component: TrackJobsListComponent },
        { path: '**', redirectTo: 'list' }
    ]
}]