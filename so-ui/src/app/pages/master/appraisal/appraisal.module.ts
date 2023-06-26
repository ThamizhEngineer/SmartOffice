import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppraisalComponent } from './comp/appraisal.componenet';
import { AppraisalService } from './appraisal.service';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { CommonService } from '../../../shared/common/common.service';

const routes: Routes = [
    {path:'',component:AppraisalComponent}
]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,SharedModule,NgbModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [AppraisalComponent],
    providers:[ AppraisalService,CommonService ]
})
export class AppraisalModule { }