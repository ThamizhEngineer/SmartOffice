import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ModalController, Events } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';

import { LeaveService } from './../leave.service';

@Component({
  selector: 'app-input',
  templateUrl: './detail.page.html',
  styleUrls: ['./leave.page.scss'],
})
export class LeaveDetailPage implements OnInit{
  
  constructor(private commonService: CommonService, private activatedRoute: ActivatedRoute, private router: Router, private events: Events, private modalCtrl: ModalController, private _service: LeaveService) { }

  data: any;
  ngOnInit() {}

  ionViewWillEnter(){
	let id = this.activatedRoute.snapshot.paramMap.get('id');
	this._service.getLeaveDataById(id).subscribe(x=>{
		
		this.data = x;
		this.data.statusColor = this.getColor(x['leaveStatus']);
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
}
