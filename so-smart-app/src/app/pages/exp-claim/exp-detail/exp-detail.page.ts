import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ExpClaimService } from './../exp-claim.service';
import { ModalController } from '@ionic/angular';
import { PhotoViewer } from '@ionic-native/photo-viewer/ngx';


@Component({
  selector: 'app-exp-detail',
  templateUrl: './exp-detail.page.html',
  styleUrls: ['./exp-detail.page.scss'],
})
export class ExpDetailPage implements OnInit {
  data: any;
  isHidden:boolean=false;
  constructor(private photoViewer: PhotoViewer,private _service:ExpClaimService,private activatedRoute: ActivatedRoute, private router: Router, private modalCtrl: ModalController) { }

  ngOnInit() {
  }

  ionViewWillEnter(){
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this._service.getExpenseClaimDataById(id).subscribe(x=>{
      
	  this.data = x;
      this.data = this.data.optional
      this.data.statusColor = this.getColor(this.data.expenseClaimStatus);
      if(this.data.expenseClaimBills.length){
        this.data.expenseClaimBills.forEach(x=>{
          this._service.getDocument(x.docId).subscribe(y =>{
            let imageData = new Uint8Array(y);
            x.attachmentImage = 'data:image/png;base64,'+this.encode(imageData);
          },error => {
			  console.log(error.message)
		  });
        },error => {
			console.log(error.message)
		});
      }
    });
  }

  viewPH(imageFile){
	this.photoViewer.show(imageFile);
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
		case 'VALIDATION-PENDING':	
			d = 'warning';
			break;
		case 'N1-REJECTED':
		case 'N2-REJECTED':
		case 'VALIDATION-REJECTED':	
			d = 'danger';
			break;
		case 'SETTLED':
			d = 'Tertiary';
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

  encode (input) {
		var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;

		while (i < input.length) {
			chr1 = input[i++];
			chr2 = i < input.length ? input[i++] : Number.NaN; // Not sure if the index 
			chr3 = i < input.length ? input[i++] : Number.NaN; // checks are needed here

			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;

			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output += keyStr.charAt(enc1) + keyStr.charAt(enc2) +
					  keyStr.charAt(enc3) + keyStr.charAt(enc4);
		}
		return output;
	}
	
	openPreview(img) {
		// this.modalCtrl.create({
		//   component: ImageModalPage,
		//   componentProps: {
		// 	img: img
		//   }
		// }).then(modal => {
		//   modal.present();
		// });
	}

}
