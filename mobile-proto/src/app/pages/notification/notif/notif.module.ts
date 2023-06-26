import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { NotifPage } from './notif.page';
import { NotifService } from './notif.service';

const routes: Routes = [
  {
    path: '',
    component: NotifPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [NotifPage],
  providers:[ NotifService ]

})
export class NotifPageModule {}
