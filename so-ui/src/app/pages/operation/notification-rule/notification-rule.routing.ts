import { Routes } from '@angular/router';

import { NotificationRuleListComponent } from './comp/notification-rule-list.component';

export const NotificationRuleRoutes: Routes = [
    { path: '', component: NotificationRuleListComponent},
    { path: '**', redirectTo: '' }
];