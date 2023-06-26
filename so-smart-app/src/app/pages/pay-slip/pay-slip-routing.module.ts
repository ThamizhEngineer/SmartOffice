import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PaySlipPage } from './pay-slip.page';

const routes: Routes = [
  {
    path: '',
    component: PaySlipPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PaySlipPageRoutingModule {}
