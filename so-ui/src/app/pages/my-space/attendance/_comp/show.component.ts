import { Component ,OnInit} from '@angular/core';
import { Title }     from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { AttendanceService } from './../attendance.service';

@Component({
    selector:'transaction-attendance-show',
    templateUrl:'show.component.html',
	styleUrls:[ 'switchery.css' ]
})

export class AttendanceShowComponent implements OnInit{

    constructor(private titleService: Title, private _service: AttendanceService, private modal: NgbModal) { }
	
	coordinates:any = {latitude:null, longitude: null};
	warning:boolean = true;
	checkin:boolean = false;
	checkout:boolean = false;
	clockRunning:boolean = false;
    ngOnInit(){
		this.geo();
		
    }

	geo(){
		if(navigator.geolocation){
			navigator.geolocation.getCurrentPosition(position => {
				this.warning = false;
				this.coordinates.latitude = position.coords.latitude;
				this.coordinates.longitude = position.coords.longitude;
			});
		}
		else this.warning = true;
	}
	
	checkInTime: any;
	checkOutTime: any = null;
	checkThisLocation(){
		this._service.checkin(this.coordinates).subscribe(x=>{
			this.checkin = false;
			this.clockRunning = true;
			this.checkInTime = x;
			this.checkOutTime = null;
			this.timer();
			this.logging();
		});
	}
	
	modalReference = null;
	checkOut(confirmation){
		if(this.hours < 8){
			this.modalReference = this.modal.open(confirmation);
		}else this.confirmCheckout();
	}
	
	confirmCheckout(){
		this._service.checkOut(this.coordinates).subscribe(x=>{
			this.checkin = false;
			this.clockRunning = true;
			this.checkout = true;
			clearInterval(this.csw);
			clearInterval(this.logger);
			this.checkOutTime = x;
			if(this.modalReference) this.modalReference.close();
		});
	}
	
	logger:any;
	logging(){
		this.logger = setInterval(()=>{
			this._service.logging(this.coordinates).subscribe(x=>{
				
			});
			
		}, (60*10*1000));
		
	}
	
	seconds = 0; minutes = 0; hours = 0; stopwatch = ''; csw; 
	timer() {		
		this.csw = setInterval(()=>{
			this.seconds++;
			if (this.seconds >= 60) {
				this.seconds = 0;
				this.minutes++;
				if (this.minutes >= 60) {
					this.minutes = 0;
					this.hours++;
				}
			}		
			this.stopwatch = (this.hours ? (this.hours > 9 ? this.hours : "0" + this.hours) : "00") + ":" + (this.minutes ? (this.minutes > 9 ? this.minutes : "0" + this.minutes) : "00") + ":" + (this.seconds > 9 ? this.seconds : "0" + this.seconds);
		}, 1000);
	}
   
}