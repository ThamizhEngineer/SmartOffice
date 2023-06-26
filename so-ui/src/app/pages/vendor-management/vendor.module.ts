import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { VendorProfileComponent } from './_comp/vendor-profile.component';
import { VendorProfileEditComponent } from './_comp/vendor-profile-edit.component';
import { VendorProfileAddComponent } from './_comp/vendor-profile-add.component';
import { VendorRoutes } from './vendor.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { VendorListComponent } from './_comp/vendor-list.component';
import{VendorService} from '../vendor-management/vendor.service';
import { CommonService } from '../../shared/common/common.service';
import { FormsModule } from '@angular/forms';
import { FileModule } from './../../shared/file.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from '../operation/employee/auth-Interceptor.service';
import { SecurePipe } from './secure-pipe.component';
@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(VendorRoutes),NgbTooltipModule
    ],
    providers: [ VendorService, CommonService,{provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor, multi: true}],
    declarations: [VendorListComponent,VendorProfileComponent,
        VendorProfileEditComponent, VendorProfileAddComponent,SecurePipe]
})

export class VendorModule { }