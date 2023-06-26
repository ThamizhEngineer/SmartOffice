import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';


import { RouterModule, Routes } from '@angular/router';
import {  ExpCreatePage } from  './exp-create/exp-create.page';
import {  ExpListPage } from  './exp-list/exp-list.page';
import {  ExpDetailPage } from  './exp-detail/exp-detail.page';
import { ExpClaimService } from './exp-claim.service';

const routes: Routes = [
  {
    path: '',component:ExpListPage
  },
  {
    path: 'create',component:ExpCreatePage
  },
  {
    path: ':id',component:ExpDetailPage
  }
];

@NgModule({
  declarations: [ExpListPage,ExpCreatePage,ExpDetailPage],
  imports: [
    CommonModule,RouterModule.forChild(routes),IonicModule,FormsModule
  ],
  providers:[ExpClaimService]
})
export class ExpClaimModule { }
