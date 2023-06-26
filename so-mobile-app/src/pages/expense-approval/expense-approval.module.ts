import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExpenseApprovalPage } from './expense-approval';

@NgModule({
  declarations: [
    ExpenseApprovalPage,
  ],
  imports: [
    IonicPageModule.forChild(ExpenseApprovalPage),
  ],
})
export class ExpenseApprovalPageModule {}
