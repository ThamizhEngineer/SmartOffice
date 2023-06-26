import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ApplicationListComponent } from '../application/_comp/application-list.component';
import { ApplicationDetailComponent } from '../application/_comp/application-detail.component';
import { ApplicationRoutes } from './application.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(ApplicationRoutes),
    ],
    declarations: [ApplicationDetailComponent,ApplicationListComponent]
})

export class ApplicationModule { }
