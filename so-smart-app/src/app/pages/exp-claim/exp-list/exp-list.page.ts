import { Component, OnInit } from '@angular/core';
import { ExpClaimService } from './../exp-claim.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-exp-list',
  templateUrl: './exp-list.page.html',
  styleUrls: ['./exp-list.page.scss'],
})
export class ExpListPage implements OnInit {
  data: any;
  constructor(private _service:ExpClaimService, private utilsService: UtilsService) { }

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
    this._service.getExpenseClaimData().subscribe(x => {
      this.data = x;
      this.data=this.data.expenseClaimList;
      this.utilsService.dismissLoader();
    },error => {
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
    });
  }
}
