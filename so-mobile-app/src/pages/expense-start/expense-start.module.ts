import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExpenseStartPage } from './expense-start.component';

@NgModule({
  declarations: [
    ExpenseStartPage,
  ],
  imports: [
    IonicPageModule.forChild(ExpenseStartPage),
  ],
})
export class ExpenseStartPageModule {}
