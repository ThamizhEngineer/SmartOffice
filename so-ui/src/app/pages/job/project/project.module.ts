import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import { ProjectListComponent } from '../project/_comp/project-list.component';
import { ProjectDetailComponent } from '../project/_comp/project-detail.component';
import{ProjectRoutes} from '../project/project.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,SharedModule,
        RouterModule.forChild(ProjectRoutes),
    ],
   
    declarations: [ProjectListComponent,ProjectDetailComponent]
})


export class ProjectModule { }
