import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{ TestResultListComponent } from '../test-result/_comp/test-result-list.component';
export const TestResultRoutes: Routes = [{
    path: '',
    children: [
     
        { path: 'test-result', component: TestResultListComponent },
        { path: '**', redirectTo: 'test-result' }
    ]
}];
