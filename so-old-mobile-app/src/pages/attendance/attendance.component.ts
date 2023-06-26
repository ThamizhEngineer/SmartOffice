import { Component } from '@angular/core';
import { NavController, AlertController, ItemSliding } from 'ionic-angular';
import { ToastController } from 'ionic-angular';

import { Geolocation } from '@ionic-native/geolocation';
import { LocationAccuracy } from '@ionic-native/location-accuracy';

import { AttendanceService } from '../../services/attendance.service';
import { GlobalService } from '../../services/global.service';
import { AlertService } from '../../services/alert.service';

@Component({
    selector: 'page-attendance',
    templateUrl: 'attendance.component.html'
})

export class AttendancePage{
    coordinates:any={latitude:null,longitude:null};
    warning:boolean = true;
    checkin:boolean = false;
    checkInLoc:string;
	checkout:boolean = false;
    clockRunning:boolean = false;
    toastMessage:string=null;
    currentTime:boolean=false;
    checkInTimeout:any;
    checkOutTimeout:any;
    
    constructor(public navCtrl: NavController, 
                public toastCtrl: ToastController, 
                public alertCtrl: AlertController,
                public geolocation: Geolocation,
                public locationAccuracy: LocationAccuracy,
                public attendService: AttendanceService,
                public globalService: GlobalService,
                public alertService: AlertService){}

    ionViewDidLoad() {
        console.log('ionViewDidLoad LoginPage');
        this.checkInInit();
        this.enableGPS();
    }

    refreshMap(){
        this.enableGPS();
    }

    undo(item: ItemSliding){
        clearInterval(this.checkInTimeout);
        clearInterval(this.checkOutTimeout);
        item.close();
    }

    ionViewDidEnter(){
        
    }
    enableGPS(){
        this.locationAccuracy.canRequest().then((canRequest: boolean)=>{
           if(canRequest){
            this.locationAccuracy.request(this.locationAccuracy.REQUEST_PRIORITY_HIGH_ACCURACY).then((x)=>{
                console.log('Request successful',x);
                this.loadMap();
            },error=>{
                console.log('Error requesting location permissions',error);
            });
           }
        });
    }
          
    loadMap(){
        this.geolocation.getCurrentPosition().then((position) => {
            this.coordinates.latitude=position.coords.latitude;
            this.coordinates.longitude=position.coords.longitude;
        },(err) => {
            console.log(err);
        });
    }
    	
    checkInTime: any = localStorage.getItem('checkInTime')?localStorage.getItem('checkInTime'):null;
    checkOutInitTime:any;
    checkOutTime: any = null;

    
    checkInInit(){
        if(this.checkInTime){
            let toastMessage="Your have already checked in";
            this.alertService.presentToast(toastMessage, 2000 , "bottom");
            this.checkin = true;
            this.checkout = true;
            this.clockRunning = true;
            this.currentTime=true;
            this.timeDifference(this.currentTime,this.checkInTime);
            this.timer();
            this.logging();
        }
    }

    checkInLocation(){
       this.checkInTimeout=
            setTimeout(()=>{
                this.attendService.checkin(this.coordinates).subscribe(x=>{
                    let toastMessage="Your attendance has been checked in";
                    this.checkin = true;
                    this.checkout = true;
                    this.clockRunning = true;
                    this.checkInTime = x.dateTime;
                    this.checkInLoc= x.city;
                    this.checkOutTime = null;
                    this.timer();
                    this.logging();
                    this.alertService.presentToast(toastMessage, 2000, "bottom");
                    localStorage.setItem('checkInTime',this.checkInTime);
                    localStorage.setItem('checkInLocation',this.checkInLoc);
                });
            },2000);
    }

    checkOutInit(){
        this.checkOutTimeout=
            setTimeout(()=>{
                this.attendService.checkOut(this.coordinates).subscribe(x=>{
                    this.checkOutInitTime=x.dateTime;
                    this.timeDifference(this.checkOutInitTime,this.checkInTime);
                    if(this.hours<8){
                        this.checkOutConfirm();
                    }else{
                        this.checkOutLocation();
                    }
                });
            },2000);
    }

    diff:number;

    timeDifference(maxTime,minTime){
        if(this.currentTime){
            this.diff=(Date.now())-Date.parse(minTime);
        }else{
            this.diff=Date.parse(maxTime)-Date.parse(minTime);
        }
        let msec = this.diff;
        this.hours = Math.floor(msec / 1000 / 60 / 60);
        msec -=this.hours * 1000 * 60 * 60;
        this.minutes = Math.floor(msec / 1000 / 60);
        msec -= this.minutes * 1000 * 60;
        this.seconds = Math.floor(msec / 1000);
        msec -= this.seconds * 1000;
    }

    checkOutConfirm(){
        let confirm = this.alertCtrl.create({
        title: 'Less than Eight Hours!!!',
        subTitle:'Total Duration:'+this.stopwatch,
        message: 'Are you sure want to SwipeOut?',
        buttons: [
                {
                text: 'No',
                handler: () => {

                }
            },
                {
                text: 'Yes',
                handler: () => {
                    this.checkOutLocation();
                }
            }
            ]
        });
        confirm.present(); 
    }

    checkOutLocation(){
        this.toastMessage="Attendance Checked out";
        this.checkin = true;
        this.clockRunning = true;
        this.checkout = false;
        clearInterval(this.csw);
        clearInterval(this.logger);
        this.checkOutTime = this.checkOutInitTime;
        this.showToast(this.toastMessage);
        localStorage.removeItem('checkInTime');
    }

    showToast(message){
        let toast = this.toastCtrl.create({
            message: this.toastMessage,
            duration: 2000,
            position: 'bottom'
        });
        toast.onDidDismiss(() => { });
        toast.present();
    }


	logger:any;
	logging(){
		this.logger = setInterval(()=>{
			this.attendService.logging(this.coordinates).subscribe(x=>{	
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