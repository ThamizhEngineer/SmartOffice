import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router} from '@angular/router';
import { ModalController, Events } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';

import { TarService } from './../tar.service';

@Component({
  selector: 'app-input',
  templateUrl: './list.page.html',
  styleUrls: ['./tar.page.scss'],
})
export class TarListPage implements OnInit{
  
  constructor(private commonService: CommonService,  private router: Router, private events: Events, private modalCtrl: ModalController, private _service: TarService) { }

  data: any;
  ngOnInit() { }

  ionViewWillEnter(){
    this._service.getTarData().subscribe(x=>{
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
