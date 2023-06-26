import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{QuestionsQAComponent} from './_comp/qa.component';
export const TestRoutes: Routes = [{
    path: '',
    children: [
        { path: 'take-test', component:QuestionsQAComponent },
       
        { path: '**', redirectTo: 'take-test' }
    ]
}];
