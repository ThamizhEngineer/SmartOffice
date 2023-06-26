import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LoginPage } from './login';

import { MainPageModule } from './../main/main.module';

import { AndroidPermissions } from '@ionic-native/android-permissions';

@NgModule({
  declarations: [
    LoginPage,
  ],
  imports: [
    IonicPageModule.forChild(LoginPage),
    MainPageModule
  ],
  providers:[
    AndroidPermissions
  ]
})
export class LoginPageModule {}
