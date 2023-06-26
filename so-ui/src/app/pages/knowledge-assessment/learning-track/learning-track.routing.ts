import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{LearningTrackListComponent} from './learning-track-list.component';
import{LearningTrackDetailComponent} from './learning-track-detail.component';
export const LearningTrackRoutes: Routes = [{
    path: '',
    children: [
     
        { path: 'learning-track-list', component: LearningTrackListComponent },
        { path: 'learning-track-detail', component: LearningTrackDetailComponent },
        { path: '**', redirectTo: 'learning-track-list' }
    ]
}];
