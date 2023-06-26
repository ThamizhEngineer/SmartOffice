import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router } from '@angular/router';
import { ModalController, Events, AlertController } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';
import { ExpenseClaimService } from './../expense-claim.service';
import { Camera, CameraOptions } from '@ionic-native/camera/ngx';
import * as moment from 'moment'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {LoadingController, ToastController} from '@ionic/angular';
import {throwError} from 'rxjs';
import {catchError, finalize} from 'rxjs/operators';
import { environment } from '../../../../environments/environment';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-input',
  templateUrl: './create.page.html',
  styleUrls: ['./expense-claim.page.scss'],
})
export class ExpenseClaimCreatePage implements OnInit {
  public myPhoto: any;
  public error: string;
  private loading: any;
  private win: any = window;

	documentApiUrl: string = environment.baseUrl + environment.documentApiUrl;

  constructor(private commonService: CommonService, private camera: Camera,
    private router: Router, private events: Events,private http: HttpClient,
    private modalCtrl: ModalController,
    private _service: ExpenseClaimService, private alertCtrl: AlertController,
    private readonly loadingCtrl: LoadingController,
    private readonly toastCtrl: ToastController) {

  }
  attachmentImage: any;

  expenseClaimBill: any = { "billDt": "", "billedOnCompanyName": "Y", "billAmount": "", "billRemarks": "", attachmentImage: null };
  expenseClaimBills: any = [];

  data: any = { jobCode: false };
  costCenters: any;
  jobCodes: any;
  minDate: any;
  maxDate: any;
  xAsAny:any;
  

  ngOnInit() {
    // setting of the 'min' and 'max' values
    this.minDate = moment.utc().startOf('day').format('YYYY-MM-DD');
    this.maxDate = moment.utc().add(1, 'y').format('YYYY-MM-DD');

    this._service.getCostCenters().subscribe(x => {
      this.costCenters = x['content'];
    });
    this._service.getJobCodes().subscribe(x => {
      this.jobCodes = x;
    });
  }

  ionViewWillEnter() {

  }

  navigateToPage(page?: any, data?: any) {
    this.router.navigateByUrl(page, { state: { passedData: data } });
  }

  ionViewWillLeave() {
    this.events.unsubscribe('child:selected');
  }

  addBill() {
    this.expenseClaimBill=[];
    let bill = Object.assign({}, this.expenseClaimBill);
    this.expenseClaimBills.push(bill);
  }

  deleteBill(e) {
    this.expenseClaimBills.splice(e, 1);
  }

  createExpenseClaim() {
    this.expenseClaimBills.forEach(x => {
      x.billedOnCompanyName = x.billOnCompany ? 'Y' : 'N';
      x.billDt = x.billDt.substring(0, 10);
      delete x.billOnCompany;
    });
    this.data.isJobBased = (this.data.jobCode) ? 'Y' : 'N';
    delete this.data.jobCode;
    this.data.appliedDt = new Date().toISOString();
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
            this.router.navigate(['/expense-claim']);
          }
        }
      ]
    });
    await confirm.present();
  }

  chooseFile(data, elem) {
    let destinationType = ["DATA_URL", "FILE_URI", "NATIVE_URI"];
    let options: CameraOptions = {
      quality: 10,
      destinationType: this.camera.DestinationType[destinationType[0]],
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      correctOrientation: true,
      sourceType: parseInt(data),
    };
    this.camera.getPicture(options).then((imageData) => {
      elem.attachmentImage = "data:image/jpeg;base64," + imageData;

      this._service.fileupload(elem.attachmentImage).subscribe(x => {
        elem.docId = x[0].docId;
      }, (err) => {
        console.log("UPLOAD ERROR", err);
      });
    }, (err) => {
      console.log(err);
    });

  }
// -------------------------------------------------------------------------------------------


  takePhoto(e) {
    console.log("takephoto")

    const camera: any = navigator['camera'];
    camera.getPicture(imageData => {
      this.expenseClaimBill=e;
      this.expenseClaimBill.attachmentImage = this.convertFileSrc(imageData);
      this.uploadPhoto(imageData);
    }, error => this.error = JSON.stringify(error), {
        quality: 10,
        destinationType: camera.DestinationType.FILE_URI,
        sourceType: camera.PictureSourceType.CAMERA,
        encodingType: camera.EncodingType.JPEG
      });
  }

  selectPhoto(e): void {
    console.log("selectphpto")

    const camera: any = navigator['camera'];
    camera.getPicture(imageData => {
      this.expenseClaimBill=e;
      // this.expenseClaimBill.attachmentImage = this.convertFileSrc(imageData);
      this.expenseClaimBill.attachmentImage = this.win.Ionic.WebView.convertFileSrc(imageData);
      this.uploadPhoto(imageData);
    }, error => this.error = JSON.stringify(error), {
      sourceType: camera.PictureSourceType.PHOTOLIBRARY,
      destinationType: camera.DestinationType.FILE_URI,
      quality: 10,
      encodingType: camera.EncodingType.JPEG,
    });
  }


  private async uploadPhoto(imageFileUri: any) {
    console.log("upload")

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

  private readFile(file: any){
    console.log(file)
    console.log("readfile")
    const reader = new FileReader();
    reader.onloadend = () => {
      const formData = new FormData();
      const imgBlob = new Blob([reader.result], { type: file.type });
      console.log(imgBlob)
      formData.append('uploadingFiles', imgBlob, file.name);
      console.log(formData)
      this.postData(formData).subscribe(x => {
        let newX=x[0];
        console.log(newX);
        this.expenseClaimBill.docId=newX.docId;
        console.log("readfile-claim")
        console.log(this.expenseClaimBill);
      }, (err) => {
        console.log("UPLOAD ERROR", err);
      });
    };
    reader.readAsArrayBuffer(file);
  }

  private convertFileSrc(url: string): string {
    console.log("converfilecrc")
    console.log("starturl-"+url)

    if (!url) {
      return url;
    }
    if (url.startsWith('/')) {
      return window['WEBVIEW_SERVER_URL'] + '/_app_file_' + url;
    }
    if (url.startsWith('file://')) {
      return window['WEBVIEW_SERVER_URL'] + url.replace('file://', '/_app_file_');
    }
    if (url.startsWith('content://')) {
      return window['WEBVIEW_SERVER_URL'] + url.replace('content:/', '/_app_content_');
    }
    return url;
  }


  // -------------

  private jwtFile(){
		let currentUser = JSON.parse(localStorage.getItem('currentUser'));
		const httpHeader = new HttpHeaders().set('Authorization', currentUser.appToken);
		return { headers: httpHeader };
  }
  
  postData(formData: FormData): Observable<any> {
    
    let urlss=this.documentApiUrl + "dm/upload/EXPENSE-CLAIM-BILL-PROOF";
		console.log("hit-"+urlss)

		return this.http.post<boolean>(urlss, formData, this.jwtFile())
		  .pipe(
			catchError(e => this.handleError(e)),
			finalize(() => this.loading.dismiss())
		  )
      // .subscribe(ok => this.showToast(ok));
	  }

	  private handleError(error: any) {
		const errMsg = error.message ? error.message : error.toString();
		this.error = errMsg;
		return throwError(errMsg);
	  }


  // private async showToast(ok: boolean | {}) {
  //   if (ok === true) {
  //     const toast = await this.toastCtrl.create({
  //       message: 'Upload successful',
  //       duration: 3000,
  //       position: 'top'
  //     });
  //     toast.present();
  //   } else {
  //     const toast = await this.toastCtrl.create({
  //       message: 'Upload failed',
  //       duration: 3000,
  //       position: 'top'
  //     });
  //     toast.present();
  //   }
  // }
}
