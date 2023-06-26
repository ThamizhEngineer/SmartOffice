import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{CreateTestListComponent} from '../create-test/comp/create-test-list.component';
import{CreateTestDetailComponent} from '../create-test/comp/create-test-detail.component';
export const CreateTestRoutes: Routes = [{
    path: '',
    children: [
     
        { path: 'create-test-detail/:_id', component:CreateTestDetailComponent },
        { path: 'test-list', component: CreateTestListComponent },
        { path: '**', redirectTo: 'test-list' }
    ]
}];
