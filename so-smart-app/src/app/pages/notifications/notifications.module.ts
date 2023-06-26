import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NotificationsPageRoutingModule } from './notifications-routing.module';

import { NotificationsPage } from './notifications.page';
import { NotificationsService } from './notifications.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NotificationsPageRoutingModule
  ],
  declarations: [NotificationsPage],
  providers:[ NotificationsService ]

})
export class NotificationsPageModule {}
