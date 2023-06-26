import { Component, OnInit } from '@angular/core';
import { ExpClaimService } from './../exp-claim.service';
import { AlertController } from '@ionic/angular';
import { Router } from '@angular/router';
import { Camera, CameraOptions } from '@ionic-native/camera/ngx';
import { LoadingController } from '@ionic/angular';
import { UtilsService } from '../../../services/utils.service';
import { throwError } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';
import { ImagePicker } from '@ionic-native/image-picker/ngx';

@Component({
  selector: 'app-exp-create',
  templateUrl: './exp-create.page.html',
  styleUrls: ['./exp-create.page.scss'],
})
export class ExpCreatePage implements OnInit {
  data: any = { jobCode: false };
  costCenters: any;
  jobCodes: any;
  error: any;
  private loading: any;
  expenseClaimBill: any = { "billDt": "", "billedOnCompanyName": "Y", "billAmount": "", "billRemarks": "", attachmentImage: null };
  expenseClaimBills: any = [];
  minDate: any;
  maxDate: any;
  isDisabled:boolean =false
  constructor(private imagePicker: ImagePicker,private _utils: UtilsService, private readonly loadingCtrl: LoadingController, private camera: Camera, private _service: ExpClaimService, private alertCtrl: AlertController, private router: Router) { }

  ngOnInit() {
    this.minDate = "2020-03-14";
    this.maxDate = "2021-12-09";

    let currentUser = JSON.parse(localStorage.getItem('currentUser'));

    this._service.getCostCenters().subscribe(x => {
      this.costCenters = x['content'];
    });
    this._service.getJobCodes(currentUser.employeeId).subscribe(x => {
      this.jobCodes = x;
    });

  }

  addBill() {
    this.expenseClaimBill = [];
    let bill = Object.assign({}, this.expenseClaimBill);
    this.expenseClaimBills.push(bill);
  }

  deleteBill(e) {
    this.expenseClaimBills.splice(e, 1);
  }

  createExpenseClaim() {
    this.isDisabled=true
    this.expenseClaimBills.forEach(x => {
      console.log(x)
      x.billedOnCompanyName = x.billOnCompany ? 'Y' : 'N';
      x.billDt = x.billDt.substring(0, 10);
      delete x.billOnCompany;
    });
    this.data.isJobBased = (this.data.jobCode) ? 'Y' : 'N';
    delete this.data.jobCode;
    // this.data.appliedDt = new Date().toISOString();
    this.data.expenseClaimBills = this.expenseClaimBills;
    this._service.applyExpenseClaim(this.data).subscribe(x => {
      this.showConfirmPopup();
    });
  }

  async showConfirmPopup() {
    const confirm = await this.alertCtrl.create({
      header: 'Success',
      message: '<strong>Expense Claim Created Successfully.</strong>',
      buttons: [
        {
          text: 'OK',
          handler: () => {
            this.router.navigate(['/home/exp-claim']);
          }
        }
      ]
    });
    await confirm.present();
  }

  openCamera(x) {
    const options: CameraOptions = {
      quality: 10,
      destinationType: this.camera.DestinationType.FILE_URI,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE
    }
    this.camera.getPicture(options).then((imageData) => {
      this.formImage(x,imageData);
    }, (err) => {
      console.log(err.message)
    });
  }

  selectPhoto(x): void {
    const options = {
      quality: 10,
      maximumImagesCount: 1,
      outputType: 0
    }
    this.imagePicker.getPictures(options).then((results) => {
      console.log(results)
      for (var i = 0; i < results.length; i++) {
          console.log('Image URI: ' + results[i]);
          this.formImage(x,results[i]);
      }
    }, (err) => {
      console.log(err.message)
     });
  }



  formImage(x, imageData) {
    this.expenseClaimBill = x;
    let base64Image = 'data:image/jpeg;base64,' + imageData;
    console.log(base64Image)
    this.expenseClaimBill.attachmentImage = this._utils.convertFileSrc(imageData);
    this.uploadPhoto(imageData);
  }

  private async uploadPhoto(imageFileUri: any) {
    this.error = null;
    this.loading = await this.loadingCtrl.create({
      message: 'Uploading...'
    });
    this.loading.present();
    window['resolveLocalFileSystemURL'](imageFileUri,
      entry => {
        entry['file'](file => this.readFile(file));
      });
  }

  private readFile(file: any) {
    console.log(file)
    console.log("readfile")
    const reader = new FileReader();
    reader.onloadend = () => {
      const formData = new FormData();
      const imgBlob = new Blob([reader.result], { type: file.type });
      console.log(imgBlob)
      formData.append('uploadingFiles', imgBlob, file.name);
      console.log(formData)
      this._service.postImage(formData)
        .pipe(
          catchError(e => this.handleError(e)),
          finalize(() => this.loading.dismiss())).subscribe(x => {
            console.log("readFile returns",x)
            let newX = x[0];
            this.expenseClaimBill.docId = newX.docId;
          }, (err) => {
            console.log("UPLOAD ERROR", err);
          });
    };
    reader.readAsArrayBuffer(file);
  }

  private handleError(error: any) {
    const errMsg = error.message ? error.message : error.toString();
    this.error = errMsg;
    return throwError(errMsg);
  }


}
