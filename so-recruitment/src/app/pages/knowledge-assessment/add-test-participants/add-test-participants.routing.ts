import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{AddTestParticipantsListComponent} from '../add-test-participants/comp/add-test-participants-list.component';
import{AddTestParticipantsDetailComponent} from '../add-test-participants/comp/add-test-participants-detail.component';
export const AddTestParticipantsRoutes: Routes = [{
    path: '',
    children: [
        { path: 'partcipants-detail', component:AddTestParticipantsDetailComponent },
        { path: 'partcipants-detail/:_id', component:AddTestParticipantsDetailComponent },
        { path: 'partcipants-list', component: AddTestParticipantsListComponent },
        { path: '**', redirectTo: 'partcipants-list' }
    ]
}];
