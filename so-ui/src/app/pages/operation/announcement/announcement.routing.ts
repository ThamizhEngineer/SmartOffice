import { Routes } from '@angular/router';

import { AnnouncementComponent } from './comp/announcement.component';

export const AnnouncementRoutes: Routes = [
    { path: 'view', component: AnnouncementComponent},
    { path: '**', redirectTo: 'view' }
];
