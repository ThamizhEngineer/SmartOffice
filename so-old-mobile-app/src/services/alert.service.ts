import { Injectable } from '@angular/core';

import { AlertController, ToastController } from 'ionic-angular';

@Injectable()
export class AlertService {

  constructor(public alertCtrl: AlertController,
              public toastCtrl: ToastController) {
    
  }

  presentAlert(message: string) {
    let alert = this.alertCtrl.create({
      title: 'Error',
      subTitle: message,
      buttons: ['Ok']
    });
    alert.present();
  }

  presentToast(message: string, duration: number, position: string){
    let toast = this.toastCtrl.create({
      message: message,
      duration: duration,
      position: position
    });
    toast.present();
  }

}
