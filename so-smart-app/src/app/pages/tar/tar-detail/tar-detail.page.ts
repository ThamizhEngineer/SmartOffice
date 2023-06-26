import { Component, OnInit } from '@angular/core';
import { TarService } from '../tar.service';
import { ActivatedRoute } from '@angular/router';
import { UtilsService } from '../../../services/utils.service'
import { AlertController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tar-detail',
  templateUrl: './tar-detail.page.html',
  styleUrls: ['./tar-detail.page.scss'],
})
export class TarDetailPage implements OnInit {
  data: any;
  isHidden:boolean=false;
  isDisabled:boolean=false;
  costCenters: any;
  jobCodes: any;
  isJobCodes: any;
  dayRanges: any;

  constructor(private _service:TarService,
    private activatedRoute:ActivatedRoute,
    private alertCtrl: AlertController,
    private utilService:UtilsService,
    private router: Router) { }

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

  ionViewWillEnter(){
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this._service.getTarDataById(id).subscribe(x=>{
      
      this.data = x;
      if(this.data.costCenterId!=null || this.data.costCenterId!="" || this.data.costCenterId!=undefined){
        this.data.costCenterId = Number(this.data.costCenterId);
      }
      if(this.data.jobId!=null || this.data.jobId!="" || this.data.jobId!=undefined){
        this.data.jobId = Number(this.data.jobId);
      }
      this.data.dayRangeId = Number(this.data.dayRangeId);
      
      if(this.data.isAddlTravelInvolved=='Y'){
        this.data.isTravel=true;
      }else{
        this.data.isTravel=false;
      }
      
      if(this.data.jobOrCc!=null || this.data.jobOrCc!="" || this.data.jobOrCc!=undefined){
        if(this.data.jobOrCc=="Y"){
          this.data.isjobCode=true;
          this.isHidden=false;
          console.log(this.isHidden)
        }else{
            this.isHidden=true;
            this.data.isjobCode=false;
            console.log(this.isHidden)
        }
      }
      this.data.statusColor = this.getColor(x['tarStatus']);
      console.log(this.data)
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

    getColor(status){
      let d = 'primary';
      switch(status){
      case 'CREATED':
      case 'APPLIED':
      case 'N1-APPROVAL-PENDING':
      case 'N1-APPROVED':
      case 'N2-APPROVAL-PENDING':
      case 'N2-APPROVED':
        d = 'warning';
        break;
      case 'N1-REJECTED':
      case 'N2-REJECTED':
        d = 'danger';
        break;
      case 'SETTLED':
        d = 'tertiary';
        break;
      case 'APPROVED':
        d = 'success';
        break;
      case 'CANCELLED':
        d = 'light';
        break;
      default:
        d = 'primary';
      }
      
      return d;
    }
}
