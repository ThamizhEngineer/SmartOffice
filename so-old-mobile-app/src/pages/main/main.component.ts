import { Component } from '@angular/core';
import { App, NavController, NavParams } from 'ionic-angular';

import { LoginPage } from '../login/login.component';

/**
 * Generated class for the MainPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-main',
  templateUrl: 'main.component.html',
})
export class MainPage {

  rootPage:any = LoginPage;
 

  constructor(public navCtrl: NavController, public navParams: NavParams, public app: App) {
        
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad MainPage');
  }

}
