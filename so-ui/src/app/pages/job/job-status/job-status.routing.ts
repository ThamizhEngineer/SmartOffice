import { Routes } from '@angular/router';

import { JobStatusListComponent } from './_comp/list.component';
// import { JobStatusDetailComponent } from './_comp/detail.component';

export const JobStatusRoutes: Routes = [
    { path: 'list', component: JobStatusListComponent },
    // { path: 'detail', component: JobStatusDetailComponent },
    { path: '**', redirectTo: 'list' },
];
