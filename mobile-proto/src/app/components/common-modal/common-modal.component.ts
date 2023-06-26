import { Component, OnInit, Input } from '@angular/core';

import { NavParams, ModalController } from '@ionic/angular';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-common-modal',
  templateUrl: './common-modal.component.html',
  styleUrls: ['./common-modal.component.scss'],
})
export class CommonModalComponent implements OnInit {

  @Input() routeData: any;
  @Input() busData:any;

  constructor(public navParams: NavParams,
              public modalCtrl: ModalController,
              private commonService:CommonService) { }

  ngOnInit() {
    
  }

  dismiss(){
    let data={};
    this.modalCtrl.dismiss(data);
  }


}
