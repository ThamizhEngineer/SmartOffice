import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../shared/shared.module';

import { JobRoutes } from './job.routing';
import { FileModule } from './../../shared/file.module';

import { JobTypeMasterComponent } from './job-type-master/job-type-master.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { ServiceMasterComponent } from './service-master/service-master.component';
import { ServiceBundleComponent } from './service-bundle/service-bundle.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GoodsMasterComponent } from './goods-master/goods-master.component';
import { ClientPoComponent } from './client-po/client-po.component';
import { UserGroupEmployeeMappingComponent, NgbdSortableHeader } from '.././job/user-group-employee-mapping/user-group-employee-mapping-list.component';
import { ProfileMasterComponent } from './profile-master/profile-master.component';
import { ProductMasterComponent } from './product-master/product-master.component';
import { UserGroupComponent } from './user-group/user-group-master.component';
import { UserGroupMasterService } from './user-group/user-group.service';
import { JobService } from './job.service';
import { PartnerService } from '././sale-order/partner-service';
import { ProjectService } from './project/project.service';
import { ClientPurchaseOrderService } from './sale-order/client-purchase-order.service';
import { CommonService } from '../../shared/common/common.service';
import {
  ReactiveFormsModule
} from '@angular/forms';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl
} from '@angular/forms';
import { OfficeMasterService } from './office-master/office-master.service';
import { UserGroupEmployeeMappingService } from './user-group-employee-mapping/user-group-employee-mapping.service';
import { TempJobComponent } from './temp-job/temp-job.component';
import { TempJobService } from './temp-job/temp-job.service';
import { TypeaheadPopupDirective } from './popup.directive';
import { TaskTypeComponent } from './task-type/task-type.component';
import { TaskTypeService } from './task-type/task-type.service';

@NgModule({
  imports: [
    CommonModule, NgbModule, FormsModule, ReactiveFormsModule, SharedModule, FileModule,
    RouterModule.forChild(JobRoutes)
  ],
  exports: [],
  providers: [JobService, CommonService, UserGroupEmployeeMappingService, ProjectService, PartnerService, ClientPurchaseOrderService, OfficeMasterService, UserGroupMasterService, TempJobService,TaskTypeService],
  declarations: [JobTypeMasterComponent, UserGroupEmployeeMappingComponent, NgbdSortableHeader, ManufacturerComponent, ServiceMasterComponent, ServiceBundleComponent,TaskTypeComponent,
    GoodsMasterComponent, ClientPoComponent, ProductMasterComponent, ProfileMasterComponent, UserGroupComponent, TempJobComponent,TypeaheadPopupDirective],

})
export class JobModule { }