import { NgModule } from '@angular/core';
import { RecurimentRoutes } from './recruitment.routing';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [CommonModule,FormsModule,NgbModule,RouterModule.forChild(RecurimentRoutes)],
    exports: [],
    declarations: [],
    providers: [],
})
export class RecruitmentModule { }
