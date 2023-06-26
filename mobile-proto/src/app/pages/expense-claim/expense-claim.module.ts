import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { ExpenseClaimService } from './expense-claim.service';

import { ExpenseClaimListPage } from './_comp/list.page';
import { ExpenseClaimDetailPage } from './_comp/detail.page';
import { ExpenseClaimCreatePage } from './_comp/create.page';
import { ImageModalPage } from './_comp/image-modal';
import { ComponentsModule } from 'src/app/components/components.module';
import { Ionic4DatepickerModule } from '@logisticinfotech/ionic4-datepicker';

const routes: Routes = [
  { path: '', component: ExpenseClaimListPage },
  { path: 'create', component: ExpenseClaimCreatePage },
  { path: ':id', component: ExpenseClaimDetailPage }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule,Ionic4DatepickerModule
  ],
  entryComponents: [ ImageModalPage ],
  declarations: [
    ExpenseClaimListPage, ExpenseClaimDetailPage, ExpenseClaimCreatePage, ImageModalPage
  ],
  providers:[ ExpenseClaimService ]
})
export class ExpenseClaimModule {}
