import { Component, OnInit } from '@angular/core';
import { TarService } from '../tar.service';
import { AlertController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tar-create',
  templateUrl: './tar-create.page.html',
  styleUrls: ['./tar-create.page.scss'],
})
export class TarCreatePage implements OnInit {
  data: any = {isjobCode : false};
  costCenters: any;
  jobCodes: any;
  isJobCodes: any;
  dayRanges: any;
  minDate:any;
  maxDate:any;
  isDisabled:boolean=false;

  constructor(private _service:TarService,private alertCtrl: AlertController,private router: Router) { }

  ngOnInit() {
    this._service.getCostCenters().subscribe(x=>{
      this.costCenters = x['content'];
    });
    this._service.getJobCode().subscribe(x=>{
      this.jobCodes = x;
      if(this.jobCodes.length==0){
        this.isJobCodes=false;
      }else{
        this.isJobCodes=true;
      }
    
    });
    this._service.getDayRanges().subscribe(x=>{
      this.dayRanges = x;
    });
  }

  createTar(){
    this.isDisabled=true
    this.data.jobOrCc = (this.data.jobCode) ? 'Y' : 'N';
    this.data.isAddlTravelInvolved  = (this.data.isTravel) ? 'Y' : 'N';
    this.data.tarStatus="CREATE";
    // delete this.data.jobCode;
    // delete this.data.isTravel;
    console.log(this.data);
    this._service.createTar(this.data).subscribe(x=>{
      this.showConfirmPopup();
    });
   }

   applyTar(){
    this.isDisabled=true
    this.data.jobOrCc = (this.data.isjobCode) ? 'Y' : 'N';
    this.data.isAddlTravelInvolved  = (this.data.isTravel) ? 'Y' : 'N';
    this.data.tarStatus="CREATE";
    // delete this.data.jobCode;
    // delete this.data.isTravel;
    console.log(this.data);
    this._service.applyTar(this.data).subscribe(x=>{
      this.showConfirmPopup();
    });
   }

   checkJob(action){
    console.log(action);
     if(action==true){
       if(this.isJobCodes==false){
        this.data.isjobCode=true;
        this.showJobAlertPopup();
      
       }
     }
   }

   async showJobAlertPopup() {
    const confirm = await this.alertCtrl.create({
      header: 'Jobs Not Available.',
      buttons: [
        {
          text: 'OK',
          handler: () => {
            
          }
        }
      ]
    });
    await confirm.present();
  }

   async showConfirmPopup() {
    const confirm = await this.alertCtrl.create({
      header: 'Success',
      message: '<strong>Travel Advance Request Applied Successfully.</strong>',
      buttons: [
        {
          text: 'OK',
          handler: () => {
            this.router.navigate(['/home/tar']);
          }
        }
      ]
    });
    await confirm.present();
  }
}
