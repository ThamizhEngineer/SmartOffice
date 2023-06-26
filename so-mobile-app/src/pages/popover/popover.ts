import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController, App, ViewController } from 'ionic-angular';

import { AuthenticationService } from '../../providers/authentication.service';

import { LoginPage } from '../login/login';

/**
 * Generated class for the PopoverPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-popover',
  templateUrl: 'popover.html',
})
export class PopoverPage {

  constructor(public navCtrl: NavController, 
              public navParams: NavParams, 
              public alertCtrl: AlertController,
              public authService: AuthenticationService,
              public viewCtrl: ViewController,
              public app: App) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad PopoverPage');
  }

  close() {
    this.viewCtrl.dismiss();
  }

  signOut(){
    let confirm = this.alertCtrl.create({
      title: 'Logout?',
      message: 'Are you sure want to Logout?',
      buttons: [
        {
          text: 'No',
          handler: () => {
            this.viewCtrl.dismiss();
          }
        },
        {
          text: 'Yes',
          handler: () => {
            this.authService.logout().subscribe(x=>{
              this.app.getRootNav().setRoot(LoginPage);
              localStorage.removeItem('currentUser');
              this.viewCtrl.dismiss();
            },error=>{
              this.app.getRootNav().setRoot(LoginPage);
              this.viewCtrl.dismiss();
            });
          }
        }]
    });
    confirm.present();
  }

}
