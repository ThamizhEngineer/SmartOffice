import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { ComponentsModule } from 'src/app/components/components.module';
import {LeaveService} from './leave.service';
import { IonicModule } from '@ionic/angular';

import { LeaveListPage } from './_comp/list.page';
import { LeaveDetailPage } from './_comp/detail.page';
import { LeaveCreatePage } from './_comp/create.page';

const routes: Routes = [
  { path: '', component: LeaveListPage },
  { path: 'create', component: LeaveCreatePage },
  { path: ':id', component: LeaveDetailPage }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule
  ],
  declarations: [ LeaveListPage, LeaveDetailPage, LeaveCreatePage ],
  providers:[ LeaveService ]


})
export class LeavePageModule {}
