import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController, AlertController } from '@ionic/angular';

@Component({
  selector: 'app-common-popover',
  templateUrl: './common-popover.component.html',
  styleUrls: ['./common-popover.component.scss'],
})
export class CommonPopoverComponent implements OnInit {

  constructor(private router:Router,
              private popoverCtrl: PopoverController,
              private alertCtrl: AlertController) { }

  ngOnInit() {}

  navigateToAccounts(){
    this.router.navigate(['/account']);
    this.popoverCtrl.dismiss();
  }

  async logout(){
    const confirm = await this.alertCtrl.create({
      header: 'Logout?',
      message: '<strong>Are you sure want to Logout?</strong>',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          cssClass: 'secondary',
          handler: () => {
            this.popoverCtrl.dismiss();
          }
        },
        {
          text: 'Okay',
          handler: () => {
           this.router.navigate(['/logout']);
           this.popoverCtrl.dismiss();
          }
        }
      ]
    });
    await confirm.present();
  }

  navigateToHelp(){
    this.popoverCtrl.dismiss();
  }



}
