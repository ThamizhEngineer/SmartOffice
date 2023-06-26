import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router} from '@angular/router';
import { ModalController, Events } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';

import { LeaveService } from './../leave.service';

@Component({
  selector: 'app-input',
  templateUrl: './list.page.html',
  styleUrls: ['./leave.page.scss'],
})
export class LeaveListPage implements OnInit{
  
  constructor(private commonService: CommonService,  private router: Router, private events: Events, private modalCtrl: ModalController, private _service: LeaveService) { }

  data: any;
  ngOnInit() { }

  ionViewWillEnter(){
    this._service.getLeaveData().subscribe(x=>{
	    this.data = x;
	});
  }

  navigateToPage(page?:any, data?:any){
    this.router.navigateByUrl(page, { state: { passedData : data } } );
  }

  ionViewWillLeave(){
    this.events.unsubscribe('child:selected');
  }
}
