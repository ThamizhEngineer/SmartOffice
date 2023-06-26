import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RecruitmentComponent } from './comp/recruitment.component';
import { Routes, RouterModule } from '@angular/router';
import { RecruitmentService } from './recruitment.service';

const routes: Routes = [
    {path:'',component:RecruitmentComponent}
]

@NgModule({
    imports: [CommonModule,FormsModule,NgbModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [RecruitmentComponent],
    providers: [RecruitmentService],
})
export class RecruitmentModule { }
