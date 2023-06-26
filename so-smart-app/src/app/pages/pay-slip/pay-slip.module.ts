import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PaySlipPageRoutingModule } from './pay-slip-routing.module';

import { PaySlipPage } from './pay-slip.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PaySlipPageRoutingModule
  ],
  declarations: [PaySlipPage]
})
export class PaySlipPageModule {}
