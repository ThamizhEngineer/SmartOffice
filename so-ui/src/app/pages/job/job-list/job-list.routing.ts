import { Routes } from '@angular/router';
import { JobListComponent } from './_comp/list.component';

export const JobListRoutes: Routes = [
    { path: '', component: JobListComponent },
    { path: '**', redirectTo: '' },
]