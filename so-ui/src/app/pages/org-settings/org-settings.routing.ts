import { Routes } from '@angular/router';
import { FiscalYearShowComponent } from './fiscal-year/_comp/show.component';
import { FiscalYearDetailComponent } from './fiscal-year/_comp/detail.component';
import { HolidaysShowComponent } from './holidays/_comp/show.component';
import { GradesShowComponent } from './grades/_comp/show.component';
import { OrgLeaveBalanceComponent } from './leave-balance/_comp/leave.component';
import { RoleAccessShowComponent } from './role-access/_comp/show.component';
import { RoleAccessDetailComponent } from './role-access/_comp/detail.component';


export const OrgSettingsRoutes: Routes = [
    { path: 'fiscal-year', component: FiscalYearShowComponent },
    { path: 'fiscal-year/:id', component: FiscalYearDetailComponent },
	{ path: 'leave-balance', component: OrgLeaveBalanceComponent },
    { path: 'leave-balance/:fyear', component: OrgLeaveBalanceComponent },
    { path: 'holidays', component: HolidaysShowComponent },
    { path: 'grades', component: GradesShowComponent },
    { path: 'role-access', component: RoleAccessShowComponent},
    { path: 'role-access/:id', component: RoleAccessDetailComponent},
    { path: 'org-goal', loadChildren:'../transaction/org-goal/org-goal.module#OrganisationalGoalModule' },
    { path: '**', redirectTo: 'fiscal-year' },
];
