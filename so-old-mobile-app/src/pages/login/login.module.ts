import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { LoginPage } from './login.component';

import { HomePageModule } from './../../pages/home/home.module';

import { AndroidPermissions } from '@ionic-native/android-permissions';



@NgModule({
  declarations: [
    LoginPage
  ],
  imports: [
    IonicPageModule.forChild(LoginPage),
    HomePageModule
  ],
  providers:[
    AndroidPermissions
  ]
})
export class LoginPageModule {}
