import { Routes } from '@angular/router';

import { JobTypeMasterComponent } from './job-type-master/job-type-master.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { ServiceMasterComponent } from './service-master/service-master.component';
import { ServiceBundleComponent } from './service-bundle/service-bundle.component';
import {  GoodsMasterComponent  } from './goods-master/goods-master.component';
import {  ProfileMasterComponent  } from './profile-master/profile-master.component';
import {  ClientPoComponent  } from './client-po/client-po.component';
import {ProductMasterComponent } from './product-master/product-master.component';
import { UserGroupComponent } from './user-group/user-group-master.component';
import { UserGroupEmployeeMappingComponent } from './user-group-employee-mapping/user-group-employee-mapping-list.component';
import { TempJobComponent } from './temp-job/temp-job.component';
import { SiteLocationModule } from '../master/site-location/site-location.module';
import { TaskTypeComponent } from './task-type/task-type.component';

export const JobRoutes: Routes = [
    { path: 'job-list', loadChildren: './job-list/job-list.module#JobListModule' },
    { path: 'sale-order', loadChildren: './sale-order/sale-order.module#SaleOrderModule' },
    { path: 'job-type', component: JobTypeMasterComponent },
    { path: 'manufacturer', component: ManufacturerComponent },
    { path: 'service-master', component: ServiceMasterComponent },
    { path: 'task-type', component: TaskTypeComponent },
    { path: 'service-bundle', component: ServiceBundleComponent },
    { path: 'goods-master', component: GoodsMasterComponent },
    { path: 'profile-master', component: ProfileMasterComponent },
    { path: 'client-po', component: ClientPoComponent },
    { path: 'user-group-master', component: UserGroupComponent},
    { path: 'user-group-employee-mapping', component: UserGroupEmployeeMappingComponent },
    { path: 'product-master', component: ProductMasterComponent },
    { path: 'project',loadChildren:'./project/project.module#ProjectModule'},
    { path: 'track-jobs', loadChildren: './track-jobs/track-jobs.module#TrackJobsModule' },
    {path:'resource-scheduler',loadChildren:'./resource-scheduler/resource-scheduler.module#ResourceSchedulerModule'},
    { path:'job-plan',loadChildren:'./job-plan/job-plan.module#JobPlanModule' },
    { path:'business-units',loadChildren:'./business-units/business-module#BusinessModule' },
    {path:'job-status',loadChildren:'./job-status/job-status.module#JobStatusModule'},
    {path:'job-feedback',loadChildren:'./job-feedback/job-feedback.module#JobFeedbackModule'},
    {path:'job-report',loadChildren:'./job-report/job-report.module#JobReportModule'},
    {path:'job-report',loadChildren:'./job-report/job-report.module#JobReportModule'},
    { path: 'purchase-order',loadChildren:'./purchase-order/purchase-order.module#PurchaseOrderModule'},
    { path: 'site-location',loadChildren:'../master/site-location/site-location.module#SiteLocationModule'},
    { path: 'material-master',loadChildren:'../master/material/material.module#MaterialModule'},
    { path: 'temp-job', component: TempJobComponent },
    { path: 'job-type', component: JobTypeMasterComponent },
    { path: '**', redirectTo: '' },
];
 