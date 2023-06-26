import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { DashboardService } from './/dashboard.service';

import { IonicModule } from '@ionic/angular';

import { DashboardPage } from './dashboard.page';
import { ComponentsModule } from 'src/app/components/components.module';
//import { ChartsModule } from 'ng2-charts';
const routes: Routes = [
  { path: '', component: DashboardPage }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule,
  //  ChartsModule
  ],
  declarations: [
    DashboardPage
  ],
  providers:[ DashboardService ]
})
export class DashboardPageModule {}
