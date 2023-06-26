import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExpenseDetailPage } from './expense-detail.component';

@NgModule({
  declarations: [
    ExpenseDetailPage,
  ],
  imports: [
    IonicPageModule.forChild(ExpenseDetailPage),
  ],
})
export class ExpenseDetailPageModule {}
