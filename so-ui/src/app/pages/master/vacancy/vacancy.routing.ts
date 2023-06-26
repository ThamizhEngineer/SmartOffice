import { Routes } from '@angular/router';

import { VacancyListComponent } from '../vacancy/_comp/vacancy-list.component';
import { VacancyDetailComponent } from '../vacancy/_comp/vacancy-detail.component';
export const VacancyRoutes: Routes = [{
    path: '',
    children: [
       { path: 'vacancy-list', component: VacancyListComponent,  pathMatch: 'full' },
      {path:'vacancy-detail', component: VacancyDetailComponent, pathMatch:'full'},
        { path: '**', redirectTo: 'vacancy-list' }
      ],
    
}];
