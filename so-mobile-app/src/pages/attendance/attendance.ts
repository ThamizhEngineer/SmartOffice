import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';

import { AlertService } from '../../services/alert.service';

import { AttendanceService } from '../../providers/attendance.service';
import { MySpaceService } from './../../providers/my-space.service';
import { CommonService } from './../../providers/common.service';

import { Geolocation } from '@ionic-native/geolocation';
import { LocationAccuracy } from '@ionic-native/location-accuracy';


/**
 * Generated class for the AttendancePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-attendance',
  templateUrl: 'attendance.html',
})
export class AttendancePage {

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
  checkInToggle:boolean=false;
  checkOutToggle:boolean=false;
  confirmPopup:any;
  myAttendanceList:any=[];
  todayAttendanceList:any={};
  todayDate:any;
  checkInTime: any;
  checkOutInitTime:any;
  checkOutTime: any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public alertCtrl: AlertController,
              public commonService: CommonService,
              public attendService: AttendanceService,
              public mySpaceService: MySpaceService,
              public alertService: AlertService,
              public geolocation: Geolocation,
              public locationAccuracy: LocationAccuracy) {
               
               }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AttendancePage');
    this.enableGPS();
    this.getMyAttendanceList();
  }

  getMyAttendanceList(){
    this.todayDate=this.commonService.formatDate(new Date()); 
    this.mySpaceService.getMyAttendance().subscribe((x)=>{
        console.log('My Attendance',x);
        this.myAttendanceList=x;
        this.myAttendanceList.forEach(element => {
            if(element.attendanceDt==this.todayDate){
                this.todayAttendanceList=element;
                console.log(' this.todayAttendanceList', this.todayAttendanceList);
                this.checkInTime = this.todayAttendanceList.startDt;
                this.checkInInit();
                if(this.todayAttendanceList.endDt){
                this.checkOutTime = this.todayAttendanceList.endDt;
                clearInterval(this.csw);
                clearInterval(this.logger);  
                this.stopwatch = this.todayAttendanceList.totalTimeLogged + " hours";
                  
                }
            }
            else{
                this.todayAttendanceList=null;
                this.checkInTime=null;
            }
        });
    },(err)=>{
        
    });
  }

  refreshMap(){
    this.enableGPS();
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
       }else{
        this.loadMap(); 
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

  checkInInit(){
    this.checkInToggle = true;
    this.checkin = true;
    this.checkout = true;
    this.clockRunning = true;
    this.currentTime=true;
    this.timeDifference(this.currentTime,this.checkInTime);
    this.timer();
    this.logging();
    let toastMessage="Your have already checked in";
    this.alertService.presentToast(toastMessage, 1000 , "bottom");
  }

  checkInLocation(){
    this.checkInTimeout=
        setTimeout(()=>{
            this.attendService.checkin(this.coordinates).subscribe(x=>{
                this.checkInToggle=true;
                this.checkin = true;
                this.checkout = true;
                this.clockRunning = true;
                this.checkInTime = x.dateTime;
                this.checkInLoc= x.city;
                this.checkOutTime = null;
                this.timer();
                this.logging();
                let toastMessage="Your attendance has been checked in";
                this.alertService.presentToast(toastMessage, 1000, "bottom");
            },
            error=>{
            let toastMessage="Issue with Checkin Service";
            this.checkInToggle=false;
            this.alertService.presentToast(toastMessage, 1000, "bottom");
            });
        },500);
    }

  checkOutInit(tempCheckout){
    if(tempCheckout){
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
            },error=>{
                let toastMessage="Issue with Checkout Service";
                this.checkOutToggle=false;
                this.alertService.presentToast(toastMessage, 1000, "bottom");
            });
        },500);
    }
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
      this.confirmPopup = this.alertCtrl.create({
      title: 'Less than Eight Hours!!!',
      subTitle:'Total Duration:'+this.stopwatch,
      message: 'Are you sure want to SwipeOut?',
      buttons: [
                {
                    text: 'No',
                    handler: () => {
                       this.checkOutToggle=false;
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
      this.confirmPopup.present(); 
  }

  checkOutLocation(){
    let toastMessage="Attendance Checked out";
    this.checkin = true;
    this.clockRunning = true;
    this.checkout = false;
    clearInterval(this.csw);
    clearInterval(this.logger);
    this.checkOutTime = this.checkOutInitTime;
    this.alertService.presentToast(toastMessage, 1000, "bottom");
    return;
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
