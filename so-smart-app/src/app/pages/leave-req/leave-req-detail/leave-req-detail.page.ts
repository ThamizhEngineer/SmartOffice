import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { LeaveReqService } from './../leave-req.service';

@Component({
  selector: 'app-leave-req-detail',
  templateUrl: './leave-req-detail.page.html',
  styleUrls: ['./leave-req-detail.page.scss'],
})
export class LeaveReqDetailPage implements OnInit {
  data:any
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalCtrl: ModalController,private _service:LeaveReqService) { }

  ngOnInit() {
  }

  ionViewWillEnter(){
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this._service.getLeaveDataById(id).subscribe(x=>{
      
      this.data = x;
      this.data.statusColor = this.getColor(x['leaveStatus']);
    });
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
