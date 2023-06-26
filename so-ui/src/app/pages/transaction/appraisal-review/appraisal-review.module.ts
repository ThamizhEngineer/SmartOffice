import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FileModule } from '../../../shared/file.module';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { N1AppraisalReviewComponent } from './comp/n1-appraisal-review.component';
import { EmpAppraisalReviewComponent } from './comp/emp-appraisal-review.component';
import { DirAppraisalReviewComponent } from './comp/dir-appraisal-review.component';
import { AppraisalReivewService } from './appraisal-review.service';

const routes: Routes = [
    {path:'n1-view',component:N1AppraisalReviewComponent},
    {path:'emp-view',component:EmpAppraisalReviewComponent},
    {path:'dir-view',component:DirAppraisalReviewComponent},    
]

@NgModule({
    imports: [CommonModule,NgbModule,FormsModule,FileModule,SharedModule,ReactiveFormsModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [N1AppraisalReviewComponent,EmpAppraisalReviewComponent,DirAppraisalReviewComponent],
    providers: [AppraisalReivewService],
})
export class AppraisalReviewModule { }
