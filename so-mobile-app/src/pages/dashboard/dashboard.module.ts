import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { DashboardPage } from './dashboard';

import { TabsLayout1Module } from '../../components/tabs/layout-1/tabs-layout-1.module';
import { PopoverPageModule } from '../popover/popover.module';

@NgModule({
  declarations: [
    DashboardPage,
  ],
  imports: [
    IonicPageModule.forChild(DashboardPage),
    TabsLayout1Module,
    PopoverPageModule
  ],
  exports:[
    DashboardPage
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DashboardPageModule {}
