import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AndroidPermissions } from '@ionic-native/android-permissions';

import { MainPage } from '../main/main';

import { AlertService } from '../../services/alert.service';

import { AuthenticationService } from "./../../providers/authentication.service";


/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  model: any= {};

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public permission: AndroidPermissions,
              public authService: AuthenticationService,
              public alertService: AlertService
              ) { }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
    this.androidPermission();
  }

  login(){
    this.authService.login(this.model.email,this.model.password)
        .subscribe(
          (data)=>{
            this.navCtrl.push(MainPage);
          },
          error=>{
            this.alertService.presentAlert('Invalid Username/Password');
          });
  }

  androidPermission(){
    this.permission.requestPermissions([this.permission.PERMISSION.ACCESS_COARSE_LOCATION,
                                          this.permission.PERMISSION.ACCESS_FINE_LOCATION]);
  }

}
