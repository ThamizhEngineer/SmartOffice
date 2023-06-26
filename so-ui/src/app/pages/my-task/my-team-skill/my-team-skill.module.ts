import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Routes, RouterModule } from '@angular/router';
import {EmployeeService} from '../../operation/employee/employee.service';
import { MySkillService } from '../../my-space/my-skill/my-skill.service';
import { SharedModule } from '../../../shared/shared.module';


import { MyTeamSkillListComponent } from './_comp/my-team-skill-list.component';
import { MyTeamSkillComponent } from './_comp/my-team-skill.component';
import { TypeaheadPopupDirective } from '../popup.directive';

const routes: Routes = [
    { path: '', component: MyTeamSkillListComponent },
    { path: 'view/:id', component: MyTeamSkillComponent },
    { path: '**',redirectTo: '' }
]

@NgModule({
    imports: [CommonModule,SharedModule,FormsModule,NgbModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [MyTeamSkillListComponent,MyTeamSkillComponent],
    providers:[ EmployeeService,MySkillService,TypeaheadPopupDirective ]
})
export class MyTeamSkillProfileModule { }