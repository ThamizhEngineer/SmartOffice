import { Routes } from '@angular/router';

//import { ApplicationListComponent } from '../application/_comp/application-list.component';
import { ProjectListComponent } from '../project/_comp/project-list.component';
import { ProjectDetailComponent } from '../project/_comp/project-detail.component';
export const ProjectRoutes: Routes = [{
    path: '',
    children: [
        { path: 'project-detail/:_id', component: ProjectDetailComponent},
        { path: 'project-new', component: ProjectDetailComponent},
        { path: 'project-list', component: ProjectListComponent },
        { path: '**', redirectTo: 'project-list' }
    ]
}];
