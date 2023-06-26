import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, Platform } from 'ionic-angular';

import { CommonService } from '../../providers/common.service';
import { MySpaceService } from '../../providers/my-space.service';
import { LoadingService } from '../../services/loading-service';
import { ToastService } from '../../services/toast-service';

import { File } from '@ionic-native/file';
import { FileTransfer, FileTransferObject } from '@ionic-native/file-transfer';
import { FileOpener } from '@ionic-native/file-opener';

import { environment } from '../../environments/environment';

/**
 * Generated class for the PayslipPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-payslip',
  templateUrl: 'payslip.html',
})
export class PayslipPage {
  
  payslipData:any=[];

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public commonService: CommonService, 
              public mySpaceService: MySpaceService,
              public loadingService: LoadingService,
              public toastService: ToastService,
              public fileTransfer: FileTransfer,
              public file: File,
              public platform: Platform,
              public fileOpener: FileOpener) {
  }

  fileTransferObject: FileTransferObject = this.fileTransfer.create();

  ionViewDidLoad() {
    console.log('ionViewDidLoad PayslipPage');
    this.getMyPayslips();
  }

  getMyPayslips(){
    this.mySpaceService.getAllMyPayslips().subscribe((x)=>{
      console.log('payslipData',x);
      this.payslipData=x;
    },error=>{

    });
  }  

  downloadPayslip() {
    let path = null;
    let docuId:string="81a1b622-6845-4b72-8ebf-3ecc6bf62602";
    const url = encodeURI(environment.documentUrl+"dm/"+docuId);

    if (this.platform.is('ios')) {
      path = this.file.documentsDirectory;
    } else if (this.platform.is('android')) {
      path = this.file.externalApplicationStorageDirectory;
    }

    this.fileTransferObject.download(url, path + 'download/' + 'my_file.pdf', false ,
      {
        headers: {
          "Authorization": this.mySpaceService.getCurrentUser().appToken
        }
    }).then((entry) => {
      this.toastService.presentToast('Download Completed..');
      console.log('download complete: ' + entry.toURL());
      let downloadUrl = entry.toURL();
      this.fileOpener.open(downloadUrl, 'application/pdf');
    }, (error) => {
      // handle error
      this.toastService.presentToast('Download Failed');
    });
  }
}
