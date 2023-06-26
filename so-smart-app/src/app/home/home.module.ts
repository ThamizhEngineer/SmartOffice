import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { HomePage } from './home.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild([
      {
        path: 'home',
        component: HomePage,
        children: [
          {
            path: 'dashboard',
            loadChildren: () => import('../pages/dashboard/dashboard.module').then(m => m.DashboardPageModule)
          },
          {
            path: 'notifications',
            loadChildren: () => import('../pages/notifications/notifications.module').then(m => m.NotificationsPageModule)
          },
          {
            path: 'attendance',
            loadChildren: () => import('../pages/attendance/attendance.module').then(m => m.AttendancePageModule)
          },
          {
            path: 'settings',
            loadChildren: () => import('../pages/settings/settings.module').then(m => m.SettingsPageModule)
          },
          {
            path: 'tar',
            loadChildren: () => import('../pages/tar/tar.module').then(m => m.TarModule)
          },
          {
            path: 'exp-claim',
            loadChildren: () => import('../pages/exp-claim/exp-claim.module').then(m => m.ExpClaimModule)
          },
          {
            path: 'leave-req',
            loadChildren: () => import('../pages/leave-req/leave-req.module').then(m => m.LeaveReqModule)
          },
          {
            path: 'pay-slip',
            loadChildren: () => import('../pages/pay-slip/pay-slip.module').then(m => m.PaySlipPageModule)
          },
          {
            path: 'chat',
            loadChildren: () => import('../pages/chat/chat.module').then(m => m.ChatPageModule)
          }
        ]
      }
    ])
  ],
  declarations: [HomePage]
})
export class HomePageModule { }
