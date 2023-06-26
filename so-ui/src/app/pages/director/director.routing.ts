import { Routes } from '@angular/router';
import { OnBoardProfileComponent } from './on-board-profile/_comp/on-board-profile.component';
import { TravelAdvanceListComponent}from'./travel-advance/travel-advance-list.component';
import { SkillAnalysisModule } from '../transaction/skill-analysis/skill-analysis.module';

export const DirectorRoutes: Routes = [
    { path: 'on-board-profile', component: OnBoardProfileComponent },
    { path: 'job-request', loadChildren: './vacancy-request/vacancy-request.module#VacancyRequestModule' },
    { path: 'appraisal', loadChildren: '../transaction/appraisal/appraisal.module#AppraisalModule' },
    { path: 'appraisal-review', loadChildren: '../transaction/appraisal-review/appraisal-review.module#AppraisalReviewModule' },
    { path: 'tar-list', component: TravelAdvanceListComponent},
    { path: 'employee-service-record', loadChildren:'../transaction/employee-service-record/employee-service.module#EmployeeServiceModule' },
    { path: 'skill-analysis', loadChildren:'../transaction/skill-analysis/skill-analysis.module#SkillAnalysisModule' }

];
