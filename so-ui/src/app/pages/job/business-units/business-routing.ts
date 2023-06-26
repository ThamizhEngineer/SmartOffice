import { Routes } from '@angular/router';
import { BusinessListComponent} from './comp/business-list.component';
import { BusinessDetailComponent } from './comp/business-detail.component';


export const BusinessRoutes: Routes = [
    { path: 'busli', component: BusinessListComponent },
    { path: 'busdi/:id', component: BusinessDetailComponent },
    { path: 'busdi', component: BusinessDetailComponent },
    { path: '**', redirectTo: 'busli' },
];