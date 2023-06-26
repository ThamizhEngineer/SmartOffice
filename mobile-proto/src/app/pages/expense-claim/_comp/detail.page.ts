import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ModalController, Events } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';

import { ExpenseClaimService } from './../expense-claim.service';
import { ImageModalPage } from './image-modal';

@Component({
  selector: 'app-input',
  templateUrl: './detail.page.html',
  styleUrls: ['./expense-claim.page.scss'],
})
export class ExpenseClaimDetailPage implements OnInit{
  
  constructor(private commonService: CommonService, private activatedRoute: ActivatedRoute, private router: Router, private events: Events, private modalCtrl: ModalController, private _service: ExpenseClaimService) { }

  data: any;
  ngOnInit() {}

  ionViewWillEnter(){
	let id = this.activatedRoute.snapshot.paramMap.get('id');
	this._service.getExpenseClaimDataById(id).subscribe(x=>{
		
		this.data = x;
		this.data.statusColor = this.getColor(x['expenseClaimStatus']);
		if(x['expenseClaimBills'].length){
			x['expenseClaimBills'].forEach(x=>{
				this._service.getDocument(x.docId).subscribe(y =>{
					let imageData = new Uint8Array(y);
					x.attachmentImage = 'data:image/png;base64,'+this.encode(imageData);
				});
			});
		}
	});
  }

  navigateToPage(page?:any, data?:any){
    this.router.navigateByUrl(page, { state: { passedData : data } } );
  }

  ionViewWillLeave(){
    this.events.unsubscribe('child:selected');
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
		this.modalCtrl.create({
		  component: ImageModalPage,
		  componentProps: {
			img: img
		  }
		}).then(modal => {
		  modal.present();
		});
	}
}
