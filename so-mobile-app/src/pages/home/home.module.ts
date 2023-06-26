import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { HomePage } from './home';

import { BasicTableModule } from '../../components/basic-table/basic-table.module';


@NgModule({
  declarations: [
    HomePage,
  ],
  imports: [
    IonicPageModule.forChild(HomePage),
    BasicTableModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HomePageModule {}
