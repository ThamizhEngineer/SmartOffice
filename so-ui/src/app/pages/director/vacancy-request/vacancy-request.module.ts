import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';


import { VacancyRequestDirListComponent } from './comp/vacancy-request-dir-list.component';

import { VacancyRequestService } from './vacancy-request.service';

const routes: Routes = [
    {path:'',component:VacancyRequestDirListComponent}
]

@NgModule({
    imports: [ NgMultiSelectDropDownModule,CommonModule,FormsModule,NgbModule,SharedModule, RouterModule.forChild(routes)],
    exports: [],
    declarations: [VacancyRequestDirListComponent],
    providers:[VacancyRequestService]
})
export class VacancyRequestModule { }