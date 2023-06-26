import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

import { MySpaceService } from '../../providers/my-space.service';

/**
 * Generated class for the HolidaysPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-holidays',
  templateUrl: 'holidays.html',
})
export class HolidaysPage {

  year:number=2018;
  holidays:any=[];

  constructor(public navCtrl: NavController, public navParams: NavParams, public mySpaceService: MySpaceService) {
    this.getMyHolidays();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad HolidaysPage');
  }

  getMyHolidays(){
    this.mySpaceService.getMyHolidays(this.year).subscribe((x)=>{
      console.log('holidays',x);
      this.holidays=x;
    },error=>{

    });
  }

}
