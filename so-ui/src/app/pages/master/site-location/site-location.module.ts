import { NgModule } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FileModule } from '../../../shared/file.module';
import { Routes, RouterModule } from '@angular/router';

import { SiteLocationListComponent } from './comp/site-location-list.component';
import { SiteLocationDetailComponent } from './comp/site-location-detail.component';
import { siteLocationService } from './site-location.service';

const routes: Routes = [
    {path:"",component:SiteLocationListComponent},
    {path:"new",component:SiteLocationDetailComponent},
    {path:"detail/:id",component:SiteLocationDetailComponent}, 
]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,FileModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [ SiteLocationListComponent , SiteLocationDetailComponent],
    providers: [siteLocationService],
})
export class SiteLocationModule { }
