import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

import { IService } from '../../services/IService';

/**
 * Generated class for the DashboardPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-dashboard',
  templateUrl: 'dashboard.html',
})
export class DashboardPage {

  page: any;
  service: IService;
  params: any = {};

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.params.data = [
      { page: "HomePage",         icon: "home",               title: "Home"},
      { page: "JobsPage",         icon: "list",               title: "Jobs"},
      { page: "TasksPage",        icon: "md-clipboard",       title: "Tasks"},
      { page: "NotificationsPage",icon: "information-circle", title: "Notifications", badgeCount:"10" ,style:"danger"},
      { page: "ChatPage",         icon: "ios-send",           title: "Chat",          badgeCount:"3" , style:"danger"}
    ];
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DashboardPage');
  }



}
