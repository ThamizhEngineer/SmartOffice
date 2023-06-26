import { Component, OnInit } from '@angular/core';
import { TarService } from '../tar.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-tar-list',
  templateUrl: './tar-list.page.html',
  styleUrls: ['./tar-list.page.scss'],
})
export class TarListPage implements OnInit {
  data:any

  constructor(private _service:TarService, private utilsService: UtilsService) { }

  ngOnInit() {
  }

  ionViewWillEnter(){
    this.loadData();
  }

  loadData() {
    this.utilsService.presentLoader().then((x) => {
      this.fetchData();
    });
  }

  fetchData() {
    this._service.getTarData().subscribe(x => {
      this.data = x;
      this.utilsService.dismissLoader();
    },error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }
}
