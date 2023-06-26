import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';


import { VacancyDescriptionViewComponent } from './comp/vacancy-description-view.component'
import { vacancyDescriptionListComponent } from './comp/vacancy-description-list.component';
import { VacancyDecriptionService } from './vacancy-description.service';

const routes: Routes = [{
    path:'',component:vacancyDescriptionListComponent}, 
    {path:'view/:id',component:VacancyDescriptionViewComponent },
    {path:'new',component:VacancyDescriptionViewComponent } 
]

@NgModule({
    imports: [CommonModule,SharedModule, NgbModule,NgMultiSelectDropDownModule,FormsModule,RouterModule.forChild(routes),ReactiveFormsModule],
    exports: [],
    declarations: [VacancyDescriptionViewComponent,vacancyDescriptionListComponent],
    providers: [ VacancyDecriptionService ]
})
export class VacancyDescriptionModule { }