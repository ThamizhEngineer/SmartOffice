import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { EmployeeListComponent } from './_comp/employee-list.component';
import { EmployeeDetailComponent } from './_comp/employee-detail.component';
//  import { EmployeeNewComponent } from './_comp/employee-new.component';
import { EmployeeService} from '../employee/employee.service';
import { FileModule } from './../../../shared/file.module';
import { JobService} from '../../job/job.service';
import { SharedModule } from './../../../shared/shared.module';
import { EmployeeRoutes } from './employee.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DateParserService } from '../../../shared/_services/date-parser.service';
import { CommonService } from '../../../shared/common/common.service';
import { AuthInterceptor } from './auth-Interceptor.service';
import { SecurePipe } from './secure-pipe.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,ReactiveFormsModule,SharedModule,FileModule,
        RouterModule.forChild(EmployeeRoutes),
    ],
    declarations: [ EmployeeDetailComponent, EmployeeListComponent,SecurePipe  ],
    providers:[ EmployeeService, JobService,DateParserService, CommonService, {provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor, multi: true}]
})

export class EmployeeModule { }
