import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule,ReactiveFormsModule }   from '@angular/forms';

import { OrgAnalysisService } from './org-analysis.service';
import { OrgAnalysisComponent } from './comp/org-analysis.component';

const routes: Routes = [
    {path:'',component:OrgAnalysisComponent}
   
]

@NgModule({
    imports: [CommonModule,SharedModule,ReactiveFormsModule,NgbModule,FormsModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [OrgAnalysisComponent],
    providers: [OrgAnalysisService],
})
export class OrgAnalysisModule { }
