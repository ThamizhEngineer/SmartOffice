import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';

import { DashboardPage } from './dashboard.component';

@NgModule({
  declarations: [
    DashboardPage
  ],
  imports: [
    IonicPageModule.forChild(DashboardPage)
  ],
  providers:[
  ]
})
export class DashboardPageModule {}
