import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';

import { DashboardService } from './dashboard.service';

const DashboardRoutes: Routes = [{
    path: '',
    component: DashboardComponent
}];

@NgModule({
    imports: [
        CommonModule, NgbModule,ChartsModule,
        RouterModule.forChild(DashboardRoutes), 
    ],
	providers:[ DashboardService ],
    declarations: [DashboardComponent]
})

export class DashboardModule { }
