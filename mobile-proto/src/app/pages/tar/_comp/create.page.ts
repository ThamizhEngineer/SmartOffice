import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router} from '@angular/router';
import { ModalController, Events, AlertController } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';
import * as moment from 'moment'

import { TarService } from './../tar.service';

@Component({
  selector: 'app-input',
  templateUrl: './create.page.html',
  styleUrls: ['./tar.page.scss'],
})
export class TarCreatePage implements OnInit{
  
  constructor(private commonService: CommonService,  private router: Router, private events: Events, private modalCtrl: ModalController, private _service: TarService,  private alertCtrl: AlertController) { }

  data: any = {jobCode : false};
  costCenters: any;
  jobCodes: any;
  dayRanges: any;
  minDate:any;
  maxDate:any;
  ngOnInit() { 
	// setting of the 'min' and 'max' values
this.minDate = moment.utc().startOf('day').format('YYYY-MM-DD');
this.maxDate = moment.utc().add(1, 'y').format('YYYY-MM-DD');
    
	this._service.getCostCenters().subscribe(x=>{
		this.costCenters = x['content'];
	});
	this._service.getJobCodes().subscribe(x=>{
		this.jobCodes = x;
	});
	this._service.getDayRanges().subscribe(x=>{
		this.dayRanges = x;
	});
  }

  ionViewWillEnter(){
  }

  navigateToPage(page?:any, data?:any){
    this.router.navigateByUrl(page, { state: { passedData : data } } );
  }

  ionViewWillLeave(){
    this.events.unsubscribe('child:selected');
  }
  
  createTar(){
	 this.data.jobOrCc = (this.data.jobCode) ? 'Y' : 'N';
	 this.data.isAddlTravelInvolved  = (this.data.isTravel) ? 'Y' : 'N';
	 delete this.data.jobCode;
	 delete this.data.isTravel;
	 this._service.applyTar(this.data).subscribe(x=>{
		this.showConfirmPopup();
	});
  }
  
  async showConfirmPopup(){
    const confirm = await this.alertCtrl.create({
       header: 'Success',
       message: '<strong>Travel Advance Request Created Successfully.</strong>',
       buttons: [
	     {
           text: 'OK',
           handler: () => {
            this.router.navigate(['/tar']);
           }
         }
       ]
     });
     await confirm.present();
    }
}

