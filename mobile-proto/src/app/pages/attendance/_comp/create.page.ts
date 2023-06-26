import { Component, OnInit  } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';
import { Router} from '@angular/router';
import { ModalController, Events } from '@ionic/angular';
import { CommonModalComponent } from 'src/app/components/common-modal/common-modal.component';
import { Geolocation } from '@ionic-native/geolocation/ngx';
import { NativeGeocoder, NativeGeocoderResult, NativeGeocoderOptions} from '@ionic-native/native-geocoder/ngx';
import { AttendanceService } from './../../../services/attendance.service';
import { AuthUser } from './../../../vo/authUser';
import { AlertController,ToastController} from '@ionic/angular';
import { LocationAccuracy } from '@ionic-native/location-accuracy/ngx';
import { AndroidPermissions } from '@ionic-native/android-permissions/ngx';


@Component({
  selector: 'app-input',
  templateUrl: './create.page.html',
  styleUrls: ['./attendance.page.scss'],
})
export class AttendanceCreatePage implements OnInit{
  coordinates:any={latitude:null,longitude:null};
  address:string="";
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

  //new
  authUser : AuthUser;
  disableCheckInButton:boolean=false;
  disablleCheckOutButton:boolean=true;
  checkInTimeToggle:boolean=false;
  checkOutTimeToggle:boolean=false;
  
  constructor(public toastCtrl: ToastController,public alertController: AlertController,private commonService: CommonService,  private router: Router,  private attendanceService:AttendanceService,
    private events: Events, private modalCtrl: ModalController,public locationAccuracy: LocationAccuracy,
    private geolocation: Geolocation,private androidPermissions: AndroidPermissions,
    private nativeGeocoder: NativeGeocoder) { }


  ngOnInit() { 
    this.authUser = new AuthUser();
    this.checkGPSPermission();
  }

  getMyAttendanceList(){
    this.todayDate=this.commonService.formatDate(new Date()); 
    this.attendanceService.getMyAttendance().subscribe((x)=>{
        console.log('My Attendance',x);
        this.myAttendanceList=x;
        this.myAttendanceList.forEach(element => {
          let d1 = new Date(this.convertDate(element.attendanceDt)); let d2 = new Date(this.todayDate);
          let attDate = d1.getDate() + "-" + (d1.getMonth() + 1) + "-" + d1.getFullYear();
          let tdyDate = d2.getDate() + "-" + (d2.getMonth() + 1) + "-" + d2.getFullYear();
          console.log('My Attendance element',element);
          console.log('attendance date - '+attDate+'- Today date - '+tdyDate);
            if(attDate===tdyDate){
                this.todayAttendanceList=element;
                this.checkInTime = this.convertDate(this.todayAttendanceList.startDt);
                console.log('check-in-time-'+this.checkInTime)
                this.checkInInit();
                if(this.todayAttendanceList.endDt){
                this.checkOutTime = this.convertDate(this.todayAttendanceList.endDt);
                console.log(this.checkOutTime)
                clearInterval(this.csw);
                clearInterval(this.logger);  
                this.disablleCheckOutButton=true;
                this.disableCheckInButton=true;
                this.stopwatch = this.todayAttendanceList.totalTimeLogged + " hours";
                }
            }
            else{
                this.todayAttendanceList=null;
                this.checkInTime=null;
                console.log("else")
            }
        });
    },(err)=>{
        console.log(err)
    });
  }




  checkInInit(){
    this.checkInToggle = true;
    this.disablleCheckOutButton=false;
    this.checkin = true;
    this.checkout = true;
    this.clockRunning = true;
    this.currentTime=true;Number
    this.timeDifference(this.currentTime,this.checkInTime);
    this.timer();
    this.logging();
    let toastMessage="Your have already checked in";
    this.presentToast(toastMessage, 1000);
  }

  checkInLocation(){
            this.attendanceService.checkin(this.authUser).subscribe(x=>{
              console.log('checkin-started')
              console.log(x)
              this.disableCheckInButton=true; //disabling check-in button
              this.disablleCheckOutButton=false; //enabling check-out button
                this.checkInToggle=true;
                this.clockRunning = true;
                this.checkInTime = this.getx(x,"date");
                this.checkInLoc= this.getx(x,"loc");
                this.checkOutTime = null;
                this.timer();
                this.logging();
                let toastMessage="Your attendance has been checked in";
                console.log(toastMessage)
                this.presentToast(toastMessage, 1000);
        });
    }

    getx(input,args):string{
      var output="";
      if(args=="date"){
         output = input.dateTime
      }
      if(args=="loc"){
         output = input.city
      }
      return output
    }

    checkOutInit(){
              this.attendanceService.checkOut(this.coordinates).subscribe(x=>{
                  this.checkOutInitTime=this.getx(x,"date");
                  this.timeDifference(this.checkOutInitTime,this.checkInTime);
                  if(this.hours<8){
                    console.log("if checkout less than 8 hrs")
                    this.checkOutConfirm()
                  }else{
                    console.log("else checkout less than 8 hrs")
                    this.checkOutLocation();
                  }
          });
      }
    
 
  //   checkOutConfirm(){ //alerts not working
  //     this.confirmPopup = this.alertController.create({
  //     header: 'Less than Eight Hours!!!',
  //     subHeader:'Total Duration:'+this.stopwatch,
  //     message: 'Are you sure want to SwipeOut?',
  //     buttons: [
  //               {
  //                   text: 'No',
  //                   handler: () => {
  //                      this.checkOutToggle=false;
  //                   }
  //               },
  //               {
  //                   text: 'Yes',
  //                   handler: () => {
  //                       this.checkOutLocation();
  //                   }
  //               }
  //           ]
  //     });
  //     this.confirmPopup.present(); 
  // }

  async checkOutConfirm() {
    const alert = await this.alertController.create({
      header: 'Less than eight hours!',
      message: 'Total Duration:'+this.stopwatch,
      buttons: [
        {
          text: 'No',
          handler: () => {
            console.log('Confirm no');
          }
        }, {
          text: 'Yes',
          handler: () => {
            console.log('Confirm Yes');
            this.checkOutLocation();
          }
        }
      ]
    });

    await alert.present();
  }



  checkOutLocation(){
    let toastMessage="Attendance Checked out";
    this.disableCheckInButton=true;
    this.disablleCheckOutButton=true;
    this.checkin = true;
    this.clockRunning = true;
    this.checkout = false;
    clearInterval(this.csw);
    clearInterval(this.logger);
    this.checkOutTime = this.checkOutInitTime;
    this.presentToast(toastMessage, 1000);
    return;
  }


  loadMap() {
      this.geolocation.getCurrentPosition().then((position) => {
        this.coordinates.latitude=position.coords.latitude;
        this.coordinates.longitude=position.coords.longitude;
        this.authUser.latitude=this.coordinates.latitude;
        this.authUser.longitude=this.coordinates.longitude;
        this.getAddressFromCoords(this.coordinates.latitude, this.coordinates.longitude);
    },(err) => {
        console.log(err);
    });
  }
 
  getAddressFromCoords(lattitude, longitude) {
    console.log("getAddressFromCoords "+lattitude+" "+longitude);
    let options: NativeGeocoderOptions = {
      useLocale: true,
      maxResults: 5
  };
  
  this.nativeGeocoder.reverseGeocode(lattitude, longitude, options)
    .then((result: NativeGeocoderResult[]) => {
      this.address=JSON.stringify(result[0])
      console.log(this.address)
      console.log("address-"+this.address)
      let data = JSON.parse(this.address)
      this.authUser.city=data.subAdministrativeArea;
      this.authUser.country=data.countryName;
      this.authUser.latitude=data.latitude;
      this.authUser.longitude=data.longitude;
      this.authUser.state=data.administrativeArea;
      this.authUser.subLocality=data.subLocality;
      this.authUser.postalCode=data.postalCode;
      console.log("authUser-"+this.authUser)
    })
    .catch((error: any) => console.log(error));
  }




  // ---------------------------------------------------------------------------------------------------------------------------------------------------

    //Check if application having GPS access permission  
    checkGPSPermission() {
      this.androidPermissions.checkPermission(this.androidPermissions.PERMISSION.ACCESS_COARSE_LOCATION).then(
        result => {
          if (result.hasPermission) {
   
            //If having permission show 'Turn On GPS' dialogue
            this.askToTurnOnGPS();
          } else {
   
            //If not having permission ask for permission
            this.requestGPSPermission();
          }
        },
        err => {
          alert(err);
        }
      );
    }

    requestGPSPermission() {
      this.locationAccuracy.canRequest().then((canRequest: boolean) => {
        if (canRequest) {
          console.log("4");
        } else {
          //Show 'GPS Permission Request' dialogue
          this.androidPermissions.requestPermission(this.androidPermissions.PERMISSION.ACCESS_COARSE_LOCATION)
            .then(
              () => {
                // call method to turn on GPS
                this.askToTurnOnGPS();
              },
              error => {
                //Show alert if user click on 'No Thanks'
                alert('requestPermission Error requesting location permissions ' + error)
              }
            );
        }
      });
    }

    askToTurnOnGPS() {
      this.locationAccuracy.request(this.locationAccuracy.REQUEST_PRIORITY_HIGH_ACCURACY).then(
        () => {
          // When GPS Turned ON call method to get Accurate location coordinates
          console.log('gps switched on')
          this.loadMap()
          this.getMyAttendanceList();
        },
        error => alert('Error requesting location permissions ' + JSON.stringify(error))
      );
    }


      //tempFix for date
  convertDate(inputDates):Date {
    var day:number=0;var year:number=0;var month:number=0;
    var hrs:number=0;var min:number=0;var seconds:number=0;var miliseconds:number=0;
    var finalDate:Date;

    if(inputDates.length>3){
      year=inputDates[0];month=inputDates[1];day=inputDates[2];hrs=inputDates[3];
      min=inputDates[4];seconds=inputDates[5];//miliseconds=inputDates[6];
      finalDate= new Date(year, month-1 , day , hrs , min , seconds);
    }else{
      year=inputDates[0];month=inputDates[1];day=inputDates[2];
      finalDate = new Date(year, month-1, day); 
    }
    console.log(finalDate.toDateString());
    return finalDate;
  }
   

  // ---------Logger and timer

  logger:any;
	logging(){
		this.logger = setInterval(()=>{
			this.attendanceService.logging(this.authUser).subscribe(x=>{	
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


  async presentToast(message: string, duration: number){
    const toast = await this.toastCtrl.create({
      message: message,
      duration: duration,
      position: 'top',

    });
    toast.present();
  }

}
