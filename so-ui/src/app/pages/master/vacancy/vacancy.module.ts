import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { VacancyListComponent } from '../vacancy/_comp/vacancy-list.component';
import { VacancyDetailComponent } from '../vacancy/_comp/vacancy-detail.component';
import { VacancyRoutes } from './vacancy.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(VacancyRoutes),
    ],
    declarations: [VacancyDetailComponent,VacancyListComponent]
})

export class VacancyModule { }
