import { Component, OnInit } from '@angular/core';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { File } from '@ionic-native/file';
import { NavController } from 'ionic-angular';
import { FileOpener } from '@ionic-native/file-opener';

import { ImagePicker, ImagePickerOptions } from '@ionic-native/image-picker';
import { ToastController, Platform } from 'ionic-angular';
import{ExpenseClaimService} from '../../services/expense-claim.service';
import{UserService} from '../../services/user.service';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import{ExpenseCreateClaimPage} from '../expense-create-claim/expense-create-claim.component';
import { ExpenseClaim } from './vo/expense-claim';
import { ExpenseStartPage } from '../expense-start/expense-start.component';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
    selector: 'page-expense',
    templateUrl: 'expense.component.html'
})

export class ExpensePage implements OnInit{

  public photos: any;
  public base64Image: string;
  public results: any=[];
  pdfObj=null;
  expenseId:any;

  id:any;
  allClaims:any;
  expensesData:Array<ExpenseClaim>;
expensesBillData:any=[];
  constructor(public camera: Camera, 
              public file: File, 
              public fileOpener: FileOpener,
              public toastCtrl: ToastController,
              public navCtrl:NavController,
              public platform: Platform,
              public imagePicker: ImagePicker,
            public expenseClaimService:ExpenseClaimService,
        public userService:UserService){}

  ngOnInit(){
      this.photos=[];
      this.expenseClaimService.getAllExpenses().subscribe(x=>{
        this.expensesData=x;
        this.expensesData.forEach(x=>{
            this.expenseId=x.id;
            
           
            x.expenseClaimBills.forEach(x=>{
                this.expensesBillData.push(x);
               console.log(this.expensesBillData);
                
                
            })
        })
        
       
              });
      let currentUser = this.userService.getCurrentUser(); 
      if(currentUser){
          this.expenseClaimService.getAllByEmployeeId(currentUser.employeeId).subscribe(res=> {
             
              this.allClaims=res;
          });
      }
  }
  getColor(){
   let color:'#F7F7F7' ;
    console.log(color);
    return color;
   

  }
  viewPage(id:string){
   
        this.id=id;
    console.log(this.id);
        this.navCtrl.push(ExpenseStartPage,{param2 :this.id})
   
  }
  takePicture(){
      const options : CameraOptions = {
          quality: 50, // picture quality
          destinationType: this.camera.DestinationType.DATA_URL,
          encodingType: this.camera.EncodingType.JPEG,
          mediaType: this.camera.MediaType.PICTURE
      }
      this.camera.getPicture(options) .then((imageData) => {
      this.base64Image = "data:image/jpeg;base64,"+imageData;
      this.photos.push(this.base64Image);
      }, (err) => {
          console.log(err);
      });
  }

  deletePhoto(index){
      this.photos.splice(index, 1);
  }

  attachMedia(){
      const options: ImagePickerOptions = {
          maximumImagesCount: 5,
          width: 300,
          quality: 50
      }

      this.imagePicker.getPictures(options).then((temp) => {
          this.results=temp;
          for (var i = 0; i < this.results.length; i++) {
              this.convertToBase64(this.results[i], 'image/jpeg').then(
                  data => {
                    this.base64Image = data.toString();
                    this.photos.push(this.base64Image);
                  }, (err) => {
                      console.log(err);
                  });
              
          }
      }, (err) => {
          console.log(err);
      });
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

  createPDF(){
      let docDefinition={};
      let totalDocs=[];
      
      for(let i=0;i<this.photos.length;i++){
          var a={
              image:this.photos[i],
              width:300
          };
          totalDocs.push(a);     
      }

      docDefinition={

          content: totalDocs

      };
      this.pdfObj= pdfMake.createPdf(docDefinition);
      this.downloadPDF();
  }
  
  downloadPDF(){
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

  uploadPDF(){
      alert('uploading PDF');
  }
  viewDetail(){
    this.navCtrl.push('ExpenseApprovalPage');
  }

  expenseCreateClaimPage(){
     
        this.navCtrl.push(ExpenseCreateClaimPage);
     
     
  }
  getAllExpenses(){
      

      
  }
  
  deleteClaim(claim){
     
    this.expenseClaimService.deleteById(claim).subscribe(res=>{
        alert('Expense Deleted Successfully');
    })
}
}
