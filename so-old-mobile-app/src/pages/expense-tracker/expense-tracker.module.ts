import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExpenseTrackerPage } from './expense-tracker.component';

@NgModule({
  declarations: [
    ExpenseTrackerPage,
  ],
  imports: [
    IonicPageModule.forChild(ExpenseTrackerPage),
  ]
})
export class ExpenseTrackerPageModule {}
