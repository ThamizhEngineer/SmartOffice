import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SortablejsModule } from 'angular-sortablejs';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';

import { SharedModule } from './../../shared/shared.module';

import { FiscalYearShowComponent } from './fiscal-year/_comp/show.component';
import { FiscalYearDetailComponent } from './fiscal-year/_comp/detail.component';
import { HolidaysShowComponent } from './holidays/_comp/show.component';
import { GradesShowComponent } from './grades/_comp/show.component';
import { OrgLeaveBalanceComponent } from './leave-balance/_comp/leave.component';
import { RoleAccessShowComponent } from './role-access/_comp/show.component';
import { RoleAccessDetailComponent } from './role-access/_comp/detail.component';

import { FiscalYearService } from './fiscal-year/fiscal-year.service';
import { HolidaysService } from './holidays/holidays.service';
import { GradesService } from './grades/grades.service';
import { LeaveBalanceService } from './leave-balance/leave-balance.service';
import { RoleAccessService } from './role-access/role-access.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../../shared/common/common.service';
import { OrgSettingsRoutes } from './org-settings.routing';

@NgModule({
  imports: [
	CommonModule, NgbModule, SharedModule, FormsModule, SortablejsModule, PerfectScrollbarModule,
    RouterModule.forChild(OrgSettingsRoutes),
  ],
  providers: [ FiscalYearService,CommonService, HolidaysService, GradesService, LeaveBalanceService, RoleAccessService ],
  declarations: [ FiscalYearShowComponent, FiscalYearDetailComponent, HolidaysShowComponent, GradesShowComponent, OrgLeaveBalanceComponent, RoleAccessShowComponent, RoleAccessDetailComponent ]
})
export class OrgSettingsModule { }
