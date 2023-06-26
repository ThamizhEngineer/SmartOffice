import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { FileModule } from '../../../shared/file.module';
import { DtdiffPipe } from './_model/datediff';
import { DatePipe } from '@angular/common';

import { NgxPaginationModule } from 'ngx-pagination';

import { TrainingOpeningService } from './training-opeining.service';
import { TrainingOpeiningComponent } from './comp/training-opeining.component';
import { AssignTrainingComponent } from './comp/training-assign.component';

const routes: Routes = [
    {path:'view',component:TrainingOpeiningComponent},
    {path:'assign',component:AssignTrainingComponent}
]
@NgModule({
    imports: [CommonModule,FileModule,FormsModule,NgxPaginationModule,NgbModule,SharedModule,RouterModule.forChild(routes),ReactiveFormsModule],
    exports: [],
    declarations: [AssignTrainingComponent,TrainingOpeiningComponent,DtdiffPipe],
    providers: [TrainingOpeningService,DatePipe],
})
export class TrainingOpeiningModule { }
