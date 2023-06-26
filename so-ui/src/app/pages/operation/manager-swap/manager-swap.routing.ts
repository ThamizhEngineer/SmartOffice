import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import{ManagerSwapDetailComponent} from './manager-swap-detail.component';

export const ManagerSwapRoutes: Routes = [{
    path: '',
    children: [
     
        { path: 'manager-swap', component: ManagerSwapDetailComponent },
        { path: '**', redirectTo: 'manager-swap' }
    ]
}];
