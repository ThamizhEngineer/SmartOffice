import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { LeaveReqCreatePage } from './leave-req-create/leave-req-create.page';
import { LeaveReqDetailPage } from './leave-req-detail/leave-req-detail.page';
import { LeaveReqListPage } from './leave-req-list/leave-req-list.page';
import { LeaveReqService } from './leave-req.service';

const routes: Routes = [
  {
    path: '',component:LeaveReqListPage
  },
  {
    path: 'create',component:LeaveReqCreatePage
  },
  {
    path: ':id',component:LeaveReqDetailPage
  }
];

@NgModule({
  declarations: [LeaveReqListPage,LeaveReqCreatePage,LeaveReqDetailPage],
  imports: [
    CommonModule,RouterModule.forChild(routes),IonicModule,FormsModule
  ],
  exports: [RouterModule],
  providers:[LeaveReqService]
})
export class LeaveReqModule { }
