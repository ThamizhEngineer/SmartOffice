import { Routes } from '@angular/router';
import{TestMarkComponent} from '../take-test/comp/test-mark.component';
//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{TakeTestListComponent} from '../take-test/comp/take-test-list.component';
import{TestAcknowledgementComponent} from '../take-test/comp/test-acknowledgement';
export const TakeTestRoutes: Routes = [{
    path: '',
    children: [
        { path: 'test-mark/:_id', component:TestMarkComponent },
        { path: 'test-acknowledgement/:_id', component:TestAcknowledgementComponent },
        { path: 'test-acknowledgement/:_id/:_value', component:TestAcknowledgementComponent },
        { path: 'take-test-list', component: TakeTestListComponent },
        { path: '**', redirectTo: 'take-test-list' }
    ]
}];
