import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FileModule } from '../../../shared/file.module';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppraisalListComponent } from './comp/appraisal-list.component'
import { AppraisalDetailComponent } from './comp/appraisal-detail.component'
import { AppraisalService } from './appraisal.service';

const routes: Routes = [
    {path:':view',component:AppraisalListComponent},
    {path:':view/detail/:id',component:AppraisalDetailComponent}
]

@NgModule({
    imports: [CommonModule,NgbModule,FormsModule,FileModule,SharedModule,ReactiveFormsModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [ AppraisalListComponent,AppraisalDetailComponent],
    providers:[AppraisalService]
})
export class AppraisalModule { }