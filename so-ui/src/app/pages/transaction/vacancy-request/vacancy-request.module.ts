import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';


import { VacancyRequestListComponent } from './comp/vacancy-request-list.component';
import { VacancyRequestComponent } from './comp/vacancy-request-view.component';

import { VacancyRequestService } from './vacancy-request.service';

const routes: Routes = [
    {path:'',component:VacancyRequestListComponent},
    {path:'dir',component:VacancyRequestListComponent},
    {path:'view',component:VacancyRequestComponent},
    {path:'new',component:VacancyRequestComponent},
]

@NgModule({
    imports: [ NgMultiSelectDropDownModule,CommonModule,FormsModule,NgbModule,SharedModule, RouterModule.forChild(routes), ReactiveFormsModule],
    exports: [],
    declarations: [VacancyRequestListComponent,VacancyRequestComponent],
    providers:[VacancyRequestService]
})
export class VacancyRequestModule { }