import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{QuestionsAddDetailComponent} from './_comp/add-detail.component';
import{QuestionsAddListComponent} from './_comp/add-list.component';
export const AddRoutes: Routes = [{
    path: '',
    children: [
        { path: 'question-add-detail', component:QuestionsAddDetailComponent },
        { path: 'question-add-detail/:_id', component:QuestionsAddDetailComponent },
        { path: 'question-add-list', component: QuestionsAddListComponent },
        { path: '**', redirectTo: 'question-add-list' }
    ]
}];
