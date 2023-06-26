import { Component, OnInit } from '@angular/core';
import { NotificationsService } from './notifications.service';
import { UtilsService } from 'src/app/services/utils.service';
import { HTTP } from '@ionic-native/http/ngx';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.page.html',
  styleUrls: ['./notifications.page.scss'],
})
export class NotificationsPage implements OnInit {
  notificationArray: any
  message: any
  constructor(private service: NotificationsService, private utilsService: UtilsService) { }

  ngOnInit() {
    this.loadNotof();
  }

  loadNotof() {
    this.utilsService.presentLoader().then((x) => {
      this.fetchNotif();
    });
  }

  fetchNotif() {
    this.service.getNofifData().subscribe(x => {
      console.log(x)
      this.notificationArray = x;
      this.utilsService.dismissLoader();
    },error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }

}
