import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExpenseCreateClaimPage } from './expense-create-claim.component';

@NgModule({
  declarations: [
    ExpenseCreateClaimPage,
  ],
  imports: [
    IonicPageModule.forChild(ExpenseCreateClaimPage),
  ],
})
export class ExpenseCreateClaimPageModule {}
