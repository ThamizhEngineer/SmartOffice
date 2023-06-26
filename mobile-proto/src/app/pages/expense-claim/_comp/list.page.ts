import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router} from '@angular/router';
import { ModalController, Events } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';

import { ExpenseClaimService } from './../expense-claim.service';

@Component({
  selector: 'app-input',
  templateUrl: './list.page.html',
  styleUrls: ['./expense-claim.page.scss'],
})
export class ExpenseClaimListPage implements OnInit{
  
  constructor(private commonService: CommonService,  private router: Router, private events: Events, private modalCtrl: ModalController, private _service: ExpenseClaimService) { }

  data: any;
  ngOnInit() { }

  ionViewWillEnter(){
    this._service.getExpenseClaimData().subscribe(x=>{
      this.data = x;

      this.data.forEach(element => {
        if(element.jobName==null || element.jobName==undefined || element.jobName==""){
          element.jobName="NA";
        }
        if(element.costCenterName==null || element.costCenterName==undefined || element.costCenterName==""){
          element.costCenterName="NA";
        }

      });


      console.log(this.data)
	});
  }

  navigateToPage(page?:any, data?:any){
    this.router.navigateByUrl(page, { state: { passedData : data } } );
  }

  ionViewWillLeave(){
    this.events.unsubscribe('child:selected');
  }
}
