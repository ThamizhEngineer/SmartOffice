import { Component, OnInit } from '@angular/core';
import { LeaveReqService } from './../leave-req.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-leave-req-list',
  templateUrl: './leave-req-list.page.html',
  styleUrls: ['./leave-req-list.page.scss'],
})
export class LeaveReqListPage implements OnInit {
  data: any;
  leaveTypes: any;
  constructor(private _service: LeaveReqService, private utilsService: UtilsService) { }

  ngOnInit() {
  }

  ionViewWillEnter() {
    this.loadData();
  }

  loadData() {
    this.utilsService.presentLoader().then((x) => {
      this.fetchData();
    });
  }

  fetchData() {
    this._service.getLeaveData().subscribe(x => {
      this.data = x;
      this.utilsService.dismissLoader();
    },error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }
}
