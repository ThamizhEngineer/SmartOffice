import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'

import { FileModule } from '../../../shared/file.module';
import { VacancyComponent } from './comp/vacancy-opening.component';
import { VacancyOpeningService } from './vacancy-opening.service';
import { DtdiffPipe } from './_model/datediff';
import { DatePipe } from '@angular/common';

const routes: Routes = [
    {path:'',component:VacancyComponent}
]

@NgModule({
    imports: [CommonModule,FileModule,FormsModule,NgbModule,SharedModule,RouterModule.forChild(routes),ReactiveFormsModule],
    exports: [],
    declarations: [VacancyComponent,DtdiffPipe],
    providers: [VacancyOpeningService,DatePipe]
})
export class VacancyOpeningModule { }