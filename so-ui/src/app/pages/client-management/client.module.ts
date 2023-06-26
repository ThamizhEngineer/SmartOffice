import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ClientProfileComponent } from './_comp/client-profile.component';
import { ClientProfileEditComponent } from './_comp/client-profile-edit.component';
import { ClientProfileAddComponent } from './_comp/client-profile-add.component';
import { ClientRoutes } from './client.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { ClientListComponent } from './_comp/client-list.component';
import{ ClientService } from './client.service';
import { CommonService } from '../../shared/common/common.service';
import { SharedModule } from './../../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { FileModule } from './../../shared/file.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ReportService } from '../report/report.service';
import { MySpaceClientComponent } from './_comp/my-space-client.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from '../operation/employee/auth-Interceptor.service';
import { SecurePipe } from './secure-pipe.component';

@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,SharedModule,ReactiveFormsModule,FileModule,
        RouterModule.forChild(ClientRoutes), NgbTooltipModule
    ],
    providers: [ ClientService, CommonService,ReportService,{provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor, multi: true}],
    declarations: [ClientListComponent,ClientProfileComponent,ClientProfileEditComponent, 
        ClientProfileAddComponent,MySpaceClientComponent,SecurePipe],
    // entryComponents: [SaleOrderModelComponent],

})

export class ClientModule { }