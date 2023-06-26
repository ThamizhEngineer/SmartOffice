import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrganisationalGoalComponent } from './comp/org-goal.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonService } from '../../../shared/common/common.service';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { AppraisalService } from './org-goal.service';

const routes: Routes = [
    {path:'',component:OrganisationalGoalComponent}
]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [ OrganisationalGoalComponent ],
    providers:[ AppraisalService,CommonService ]
})
export class OrganisationalGoalModule { }