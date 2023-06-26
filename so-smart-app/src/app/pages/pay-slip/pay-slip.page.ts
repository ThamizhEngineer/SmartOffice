import { Component, OnInit } from '@angular/core';
import { UtilsService } from 'src/app/services/utils.service';
import { PaySlipService } from './pay-slip.service';
@Component({
  selector: 'app-pay-slip',
  templateUrl: './pay-slip.page.html',
  styleUrls: ['./pay-slip.page.scss'],
})
export class PaySlipPage implements OnInit {
  data:any
  constructor(private utilsService: UtilsService,private _service:PaySlipService) { }


  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.utilsService.presentLoader().then((x) => {
      this.fetchData();
    });
  }

  fetchData() {
    this._service.fetchPayouts().subscribe(x => {
      console.log(x)
      this.data = x;
      this.utilsService.dismissLoader();
    },error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }

  saveDoc(docId){
    this.utilsService.downloadFile(docId);
  }

}
