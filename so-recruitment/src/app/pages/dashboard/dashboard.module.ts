import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';

@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(DashboardRoutes), 
		AgmCoreModule.forRoot({
		  apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
		})		
    ],
    declarations: [DashboardComponent]
})

export class DashboardModule { }
