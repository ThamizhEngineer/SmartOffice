import { Routes } from '@angular/router';
import { AuthGuard } from '../auth/_guards/auth.guard';
import { ThemeComponent } from './theme.component';
import { VacancyOpeningModule } from '../pages/transaction/vacancy-opening/vacancy-opening.module'
import { TakeTestModule } from '../pages/transaction/take-test/take-test.module'

export const ThemeRoutes: Routes = [{
    path: '', component: ThemeComponent, 'canActivate': [AuthGuard],
    'children': [
        { path: 'index', loadChildren: '../pages/dashboard/dashboard.module#DashboardModule' },
        { path: 'master', loadChildren: '../pages/master/master.module#MasterModule' },	
        { path: 'settings', loadChildren: '../pages/settings/settings.module#SettingsModule' },
        { path: 'take-test', loadChildren: '../pages/transaction/take-test/take-test.module#TakeTestModule' },
        { path: 'job-openinig', loadChildren: '../pages/transaction/vacancy-opening/vacancy-opening.module#VacancyOpeningModule'},
		{ path: 'my-space', loadChildren: '../pages/my-space/my-space.module#MySpaceModule' },
        {path:'knowledge-assessment',loadChildren:'../pages/knowledge-assessment/knowledge-assessment.module#KnowledgeAssessmentModule'},
    
		
      
        { path: '', redirectTo: 'index', pathMatch: 'full' },
    ],
}
];
 