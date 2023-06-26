import { NgModule } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FileModule } from '../../../shared/file.module';
import { Routes, RouterModule } from '@angular/router';

import { OfficeCalendarListComponent } from './comp/office-calendar-list.component';
import { OfficeCalendarDetailComponent } from './comp/office-calendar-detail.component';
import { OfficeCalendarService } from './office-calendar.service';

const routes: Routes = [
    {path:"",component:OfficeCalendarListComponent},
    {path:":view/:yearId/:officeId",component:OfficeCalendarDetailComponent},
    {path:":view/:id",component:OfficeCalendarDetailComponent}
]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,FileModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [OfficeCalendarListComponent,OfficeCalendarDetailComponent],
    providers: [OfficeCalendarService],
})
export class OfficeCalendarModule { }
