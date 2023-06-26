import { Routes } from '@angular/router';
import { MetricListComponent } from './metrics/list.component';

export const AppraisalRoutes: Routes = [
     { path: 'metric-list', component: MetricListComponent },
     { path: 'org-goal', loadChildren: '../transaction/org-goal/org-goal.module#OrganisationalGoalModule' },
     { path: 'org-analysis', loadChildren:'../transaction/org-analysis/org-analysis.module#OrgAnalysisModule' },    
    { path: '**', redirectTo: 'metric-list' },
];