import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { EmployeeConventionListComponent } from './_comp/employee-convention-list.component';
import { EmployeeConventionDetailComponent } from './_comp/employee-convention-detail.component';
import { EmployeeConventionService} from './employee-convention.service';
import { FileModule } from '../../../shared/file.module';
import { JobService} from '../../job/job.service';
import { SharedModule } from '../../../shared/shared.module';
import { EmployeeConventionRoutes } from './employee-convention.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DateParserService } from '../../../shared/_services/date-parser.service';
import { CommonService } from '../../../shared/common/common.service';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,ReactiveFormsModule,SharedModule,FileModule,
        RouterModule.forChild(EmployeeConventionRoutes),
    ],
    declarations: [ EmployeeConventionDetailComponent, EmployeeConventionListComponent ],
    providers:[ EmployeeConventionService, JobService,DateParserService, CommonService ]
})

export class EmployeeConventionModule { }
