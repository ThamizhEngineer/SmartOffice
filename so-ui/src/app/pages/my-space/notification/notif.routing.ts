import { Routes, RouterModule } from '@angular/router';
import { NotificationComponent } from './comp/notif.component';

export const NotificationRoutes: Routes = [

    { path: 'notif-list', component: NotificationComponent },
    { path: '**', redirectTo: 'notif-list' }

]