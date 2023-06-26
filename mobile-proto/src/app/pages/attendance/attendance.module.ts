import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { ComponentsModule } from 'src/app/components/components.module';
import { IonicModule } from '@ionic/angular';

import { AttendanceCreatePage } from './_comp/create.page';
import { AttendanceService } from 'src/app/services/attendance.service';

const routes: Routes = [
  { path: '', component: AttendanceCreatePage },
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule
  ],
  declarations: [ AttendanceCreatePage ],
  providers:[]


})
export class AttendancePageModule {}
