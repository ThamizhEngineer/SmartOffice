
import { NavController, NavParams, LoadingController, Loading, ActionSheetController } from 'ionic-angular';
import { Headers, Http, RequestOptions, Response, URLSearchParams } from "@angular/http";
import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import{DocInfo} from '../expense/vo/DocInfo';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { File } from '@ionic-native/file';
import { Transfer, TransferObject } from '@ionic-native/transfer';
import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';
import { ExpensePage } from '../expense/expense.component';
import { Observable } from 'rxjs';
import { FileOpener } from '@ionic-native/file-opener';
import { ImagePicker, ImagePickerOptions } from '@ionic-native/image-picker';
import { ToastController, Platform } from 'ionic-angular';
import { ExpenseClaimService } from '../../services/expense-claim.service';
import { UserService } from '../../services/user.service';
import pdfMake from 'pdfmake/build/pdfmake';
import { Base64 } from '@ionic-native/base64';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import { FilePath } from '@ionic-native/file-path';
import { ExpenseClaimBill, ExpenseClaim } from '../expense/vo/expense-claim';
import { environment } from '../../environments/environment';
import { CommonService } from '../../providers/common.service';

pdfMake.vfs = pdfFonts.pdfMake.vfs;
// declare var cordova: any;
/**
 * Generated class for the ExpenseApprovalPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-expense-detail',
  templateUrl: 'expense-detail.component.html',
})
export class ExpenseDetailPage implements OnInit {
  public photos: any;
  public base64Image: string;

  public results: any = [];
  pdfObj = null;
  docInfo:DocInfo;
  saveMsg: any;
  // myPhoto:any;
  expenseClaim: ExpenseClaim
  expenseClaimBill: ExpenseClaimBill;
  expCategories: any = [];
  merchant: any = [];
  expenseId: any;
  imageURI: any;
  lastImage: string = null;
  // loading: Loading;

  documentApiUrl: string = environment.documentApiUrl;
  imageFileName: any;
  // base64: string;
  filename: string;
  // message:any;
  currentName: any
  // error:any;
  selectedImage: any
  alert: any
  id:any;
  loader: any;
  constructor(public navCtrl: NavController, public http: Http, public navParams: NavParams, public camera: Camera,
    public file: File,

    private transfer: FileTransfer,
    private base64: Base64,
    public fileOpener: FileOpener,
    private commonService: CommonService,
    public toastCtrl: ToastController,
    public loadingCtrl: LoadingController,
    public platform: Platform,
    public imagePicker: ImagePicker,
    private filePath: FilePath,
    private actionSheetCtrl: ActionSheetController,
    public expenseClaimService: ExpenseClaimService,

    public userService: UserService) {
    // const base64 = 'data:image/png;base64,';

    this.expenseId = navParams.get('data');
    this.id=navParams.get('param2');
  }
  ngOnInit() {
    this.photos = [];
    this.expenseClaim = new ExpenseClaim();
    this.expenseClaimBill = new ExpenseClaimBill();
    const base64 = 'data:image/png;base64,';
    // this.uploadBase64(base64, 'image.png').subscribe(() => {});
    this.expenseClaim.expenseClaimBills = [new ExpenseClaimBill];
    var appToken = this.commonService.jwt();

    this.expenseCategories();
    this.merchants();
    let currentUser = this.userService.getCurrentUser();

    if (currentUser) {
      this.expenseClaimService.getAllByEmployeeId(currentUser.employeeId).subscribe(res => {


      });
    }
  }
  updateExpense() {
    console.log(this.expenseId);
    if (this.expenseId != null) {
      this.expenseClaimBill.expenseClaimId = this.expenseId;
      this.expenseClaim.expenseClaimBills.push(this.expenseClaimBill);
      this.expenseClaimService.updateExpense(this.expenseId, this.expenseClaim).subscribe(x => {
        this.saveMsg = { 'type': 'success', 'text': "Expense Created Successfully" }
        console.log('x');

        console.log(this.expenseClaim);


      })
    }
    else{
      if(this.id!=null){
        this.expenseClaimBill.expenseClaimId = this.id;
        this.expenseClaim.expenseClaimBills.push(this.expenseClaimBill);
        this.expenseClaimService.updateExpense(this.id, this.expenseClaim).subscribe(x => {
          this.saveMsg = { 'type': 'success', 'text': "Expense Created Successfully" }
          console.log('x');
  
          console.log(this.expenseClaim);
      });
    
  }
}
  }

  applyExpense() {
    if(this.expenseId!=null){
    this.expenseClaimService.applyExpense(this.expenseId, this.expenseClaim).subscribe(x => {
      this.saveMsg = { 'type': 'success', 'text': "Expense Applied Successfully" }
    })
  }
  else{
    this.expenseClaimService.applyExpense(this.id, this.expenseClaim).subscribe(x => {
      this.saveMsg = { 'type': 'success', 'text': "Expense Applied Successfully" }
    })
  }
  }
  expenseAppliedToast() {
    const toast = this.toastCtrl.create({
      message: 'Expense Applied Successfully',
      duration: 3000
    });
    toast.present();
  }






  takePicture() {
    const options: CameraOptions = {
      quality: 50, // picture quality
      destinationType: this.camera.DestinationType.DATA_URL,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE
    }
    this.camera.getPicture(options).then((imageData) => {
      this.base64Image = "data:image/jpeg;base64," + imageData;
      this.photos.push(this.base64Image);
    }, (err) => {
      console.log(err);
    });
  }











// new code
public myPhoto: any;
public error: string;
private loading: any;

selectPhoto(): void {
  const camera: any = navigator['camera'];
  camera.getPicture(imageData => {
    this.myPhoto = null;
    this.uploadPhoto(imageData);
  }, error => this.error = JSON.stringify(error), {
    sourceType: camera.PictureSourceType.PHOTOLIBRARY,
    destinationType: camera.DestinationType.FILE_URI,
    quality: 100,
    encodingType: camera.EncodingType.JPEG,
  });
}

private convertFileSrc(url: string): string {
  if (!url) {
    return url;
  }
  if (!url.startsWith('file://')) {
    return url;
  }
  url = url.substr(7);
  if (url.length === 0 || url[0] !== '/') {
    url = '/' + url;
  }
  return window['WEBVIEW_SERVER_URL'] + '/_file_' + url;
}

private async uploadPhoto(imageFileUri: any) {
  this.error = null;
  this.loading = await this.loadingCtrl.create({
    // message: 'Uploading...'
  });

  this.loading.present();

  window['resolveLocalFileSystemURL'](imageFileUri,
    entry => {
      entry['file'](file => this.readFile(file));
    });
}

private readFile(file: any) {
  const reader = new FileReader();
  reader.onloadend = () => {
    const formData = new FormData();
    const imgBlob = new Blob([reader.result], {type: file.type});
    formData.append('uploadingFiles', imgBlob, file.name);
    this.postData(formData);
   
  };
  reader.readAsArrayBuffer(file);
}

private postData(formData: FormData) {

  
 
  this.http.post('http://172.16.16.102/so-document-service/dm/upload/EXPENSE-CLAIM', formData, this.jwt()).map((response: Response) => response)
          .map(res => res.json()
        )
          .catch(error => Observable.throw(error))
          .subscribe(
              data => {
               
                this.loading.dismiss();
                this.expenseClaimBill.docId=data[0].docId;
                this.presentToast('File Uploaded Successfully');
              },
             
              error => console.log(error)
          )
 
}

download(docId){
this.presentToast('docId'+ docId);
  if(docId!=null&&docId!=""&&docId!=undefined){

      this.expenseClaimService.getDocument(docId).subscribe(x=>{
        this.presentToast('Downloaded Sucessfully');
         
        this.file.writeFile(this.file.dataDirectory, 'download.jpeg', x.blob(), { replace: true }).then(fileEntry => {
          // Open the PDf with the correct OS tools
          this.fileOpener.open(this.file.dataDirectory + 'download.jpeg', 'image/jpeg');
        })
      });
      error => this.presentToast('Error While Downloading')

} 
}












  deletePhoto(index) {
    this.photos.splice(index, 1);
  }

  attachMedia() {
    const options: ImagePickerOptions = {
      maximumImagesCount: 5,
      width: 300,
      quality: 50
    }

 

    const fileTransfer: FileTransferObject = this.transfer.create();
    
    this.imagePicker.getPictures(options).then((results) => {
     
      this.presentToast("results"+results );
        
          // this.imgPreview = results[i];
          this.base64.encodeFile(results).then((base64File: string) => {
            this.presentToast(base64File +"base64File") ;
            this.http.patch('http://35.231.179.2/so-document-service/dm/base64/', base64File, this.jwt()).map((response: Response) => response)
          .map(res => res.json()
        )
          .catch(error => Observable.throw(error))
          .subscribe(
              data => {
                this.presentToast('success');
              },

              error => console.log(error)
          )
  

      
    },
          
          
          (err) => {
            console.log(err);
          }); 

        
      })
    }
    uploadMedia(){
      const options: ImagePickerOptions = {
          maximumImagesCount: 5,
          width: 300,
          quality: 50
      }
  
      this.imagePicker.getPictures(options).then((temp) => {
          this.results=temp;
         
              this.convertToBase64(this.results, 'image/jpeg').then(
                  data => {
                    this.base64Image = data.toString();
                    this.presentToast('this.base64Image');
                    this.presentToast(this.base64Image);
                  
                    this.presentToast('this.base64Image.length');
                    this.presentToast(this.base64Image.length);
                    // this.photos.push(this.base64Image);
                    this.http.patch('http://35.231.179.2/so-document-service/dm/base64/', this.docInfo, this.jwt()).map((response: Response) => response)
          .map(res => res.json()
        )
          .catch(error => Observable.throw(error))
          .subscribe(
              data => {
                this.presentToast('success');
              },

              error => console.log(error)
          )
  

      
    
                  }, (err) => {
                      console.log(err);
                  });
              
          
      }, (err) => {
          console.log(err);
      });
  }

  private jwt(paramArr?: any) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data', 'authorization': currentUser.appToken });
    if (paramArr) {
      let myParams = new URLSearchParams();
      paramArr.forEach(x => myParams.append(x.key, x.value));
      return new RequestOptions({ headers: headers, params: myParams });
    }
    else return new RequestOptions({ headers: headers });
  }

  convertToBase64(url, outputFormat) {
    return new Promise((resolve, reject) => {
      let img = new Image();
      img.crossOrigin = 'Anonymous';
      img.onload = function () {
        let canvas = <HTMLCanvasElement>document.createElement('CANVAS'),
          ctx = canvas.getContext('2d'),
          dataURL;
        canvas.height = img.height;
        canvas.width = img.width;
        ctx.drawImage(img, 0, 0);
        dataURL = canvas.toDataURL(outputFormat);
        canvas = null;
        resolve(dataURL);
      };
      img.src = url;

    });
  }
 


  // upload(){
  //   const fileTransfer: FileTransferObject = this.transfer.create();
  //   var url = 'http://35.231.179.2/so-document-service/dm/upload/EXPENSE-CLAIM'
  //   var imge = this.imageURI;
  //   let headers = new Headers();
  //   headers.append('Accept', 'application/json');
  //   headers.append('enctype', 'multipart/form-data');
  //   var options = {
  //   fileKey: 'file',
  //   fileName: this.currentName,
  //   mimeType: "image/jpg",
  //  headers:{headers:headers}
  //   };
  //   fileTransfer.upload(imge, url, options).then((results) => {
  //   alert('file uploaded successfully');
  //   }, error => {
  //   alert('server error');
  //   });
  //   (err) => {
  //   let alert = this.alert.create({
  //   title:'Warning’'

  //   })
  //   alert.present(); };
  //   }

  expenseCategories() {
    this.expenseClaimService.getAllExpenseCategory().subscribe(x => {
      this.expCategories = x;

    })
  }
  merchants() {
    this.expenseClaimService.getAllMerchants().subscribe(x => {
      this.merchant = x;
      console.log(this.merchant)
    })
  }
  presentToast(msg) {
    let toast = this.toastCtrl.create({
      message: msg,
      duration: 10000,
      position: 'bottom'
    });

    toast.onDidDismiss(() => {
      console.log('Dismissed toast');
    });

    toast.present();
  }

  downloadPDF() {
    if (this.platform.is('cordova')) {
      this.pdfObj.getBuffer((buffer) => {
        var blob = new Blob([buffer], { type: 'application/pdf' });

        // Save the PDF to the data Directory of our App
        this.file.writeFile(this.file.dataDirectory, 'expense.pdf', blob, { replace: true }).then(fileEntry => {
          // Open the PDf with the correct OS tools
          this.fileOpener.open(this.file.dataDirectory + 'expense.pdf', 'application/pdf');
        })
      });
    } else {
      // On a browser simply use download!
      this.pdfObj.download();
    }
  }

  uploadPDF() {
    alert('uploading PDF');
  }
  expenseListPage() {
    this.navCtrl.push(ExpensePage);
  }
}

