import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { MainPage } from './main.component';

import { LoginPageModule } from './../../pages/login/login.module';

@NgModule({
  declarations: [
    MainPage,
  ],
  imports: [
    IonicPageModule.forChild(MainPage),
    LoginPageModule
  ],
})
export class MainPageModule {}
