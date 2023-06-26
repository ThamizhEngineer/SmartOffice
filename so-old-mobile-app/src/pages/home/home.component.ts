import { Component,ViewChild } from '@angular/core';
import { App,Nav, AlertController } from 'ionic-angular';

import { BackgroundGeolocation, BackgroundGeolocationConfig } from '@ionic-native/background-geolocation';
import { Geolocation, Geoposition } from '@ionic-native/geolocation';
import { LocationAccuracy } from '@ionic-native/location-accuracy';

import { DashboardPage} from '../dashboard/dashboard.component';
import { MainPage } from '../main/main.component';
import { ExpensePage } from '../expense/expense.component';
import { LoginPage } from '../login/login.component';
import { ExpenseTrackerPage } from '../expense-tracker/expense-tracker.component';
import { ChatHomePage } from '../chat-home/chat-home.component';
import { TeamAttendancePage } from '../team-attendance/team-attendance.component';
import { CalendarPage } from '../calendar/calendar.component';

import { AuthenticationService } from "./../../services/authentication.service";
import { AttendanceService } from './../../services/attendance.service';
import { GlobalService } from './../../services/global.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'page-home',
  templateUrl: 'home.component.html',
})

export class HomePage {

  @ViewChild(Nav) nav: Nav;
  rootPage: any = DashboardPage;
  pages: Array<{title: string, component: any, icon: string}>;
  watch: any;
  logLocation:any;
  public positionArr: any = {'latitude': null, 'longitude':null};
  initialPositionArr: any = {'latitude': null, 'longitude':null};
  public offlinePositions:any= [];
  public unLoggedPosition: any=[];
  fullName: string;
  designation: string;
  firstName:string;
  logger:any;
  GPSEnabled:any;
  currentUser:any;

  constructor(public alertCtrl: AlertController,
              public app: App,
              public authService: AuthenticationService,
              public attendService: AttendanceService,
              public globalService: GlobalService,
              public userService: UserService,
              public backgroundGeolocation: BackgroundGeolocation,
              public locationAccuracy: LocationAccuracy,
              public geolocation: Geolocation) {
    this.pages = [
      { title: 'Dashboard'      , component: DashboardPage        , icon:'card' },
      { title: 'Team Attendance', component: TeamAttendancePage   , icon:'flag'},
      { title: 'My Calendar'    , component: CalendarPage         , icon:'calendar'},
      { title: 'Expense'        , component: ExpensePage          , icon:'camera' },
      { title: 'Expense Tracker', component: ExpenseTrackerPage   , icon:'analytics' },
      { title: 'Chat'           , component: ChatHomePage         , icon:'chatboxes' },
      { title: 'Logout'         , component: LoginPage            , icon:'log-out'}
    ];
  }

  ionViewDidEnter(){
    this.userDetails();    
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad HomePage');
    this.offlineLocations();
    
  }

  enableGPS(){
    this.locationAccuracy.canRequest().then((canRequest: boolean)=>{
      if(canRequest){
        this.locationAccuracy.request(this.locationAccuracy.REQUEST_PRIORITY_HIGH_ACCURACY).then((x)=>{
            console.log('Request successful',x);
            setTimeout(()=>{
              this.startTracking();
            },2000);
        },error=>{
            this.enableGPS();
        });
      }
     });
  }

  userDetails(){
    let currentUser=JSON.parse(localStorage.getItem('currentUser'));
    this.fullName=currentUser.firstName +" "+ currentUser.lastName;
    this.designation=currentUser.empDesignation;

    //For Temp customer Login
    this.firstName=currentUser.firstName;
    if(this.firstName!="Karthik"){
      this.enableGPS();
      this.startTracking();
    }

  }

  startTracking() {
    const config : BackgroundGeolocationConfig = {
      desiredAccuracy: 0,
      stationaryRadius: 20,
      distanceFilter: 10,
      debug: false,
      interval: 1800000,
      notificationTitle:"Smart Office background tracking",
      notificationText:"Tracking in progress"
    };

      this.backgroundGeolocation.configure(config).subscribe((location) => {
        console.log('BackgroundGeolocation:  ' + location.latitude + ',' + location.longitude);
        this.initialPositionArr={latitude: location.latitude, longitude: location.longitude};
          if(this.globalService.isConnected()){
            this.attendService.logging(this.initialPositionArr).subscribe((location)=>{
              console.log("backgeo,Location is logged");
            },(err)=>{
              console.log("Attendance service fails");
            });
          }else{
            console.log("offline Logging saves to localstorage");
            this.offlineLocationSaving(this.initialPositionArr);
          }
        }, (err) => {
          console.log("Background Tracking fails");
      });
    this.backgroundGeolocation.start();
    this.logging();
  }

  offlineLocationSaving(position){
    this.offlinePositions.push(position);
    localStorage.setItem("offlinepositions",JSON.stringify(this.offlinePositions));
  }

 
  offlineLocations(){
    this.unLoggedPosition=JSON.parse(localStorage.getItem('offlinepositions'));
    if(this.unLoggedPosition && this.globalService.isConnected()){
      this.attendService.multipleLogging().subscribe((location)=>{
       console.log("offline locations,Location is logged");
      });
      localStorage.removeItem('offlinepositions');
    }
    
  }

	
	logging(){
    let options = {
      maximumAge:0,
      timeout:120000,
      enableHighAccuracy: false
    };

    setInterval(()=>{
      this.watch=this.geolocation.watchPosition(options).filter((p: any) => p.code === undefined).subscribe((position: Geoposition) => {
        this.positionArr = {latitude: position.coords.latitude, longitude: position.coords.longitude};
        console.log("this.po",this.positionArr);
        if(this.globalService.isLoggedIn()){
          if(this.globalService.isConnected()){
            this.attendService.logging(this.positionArr).subscribe(location=>{
              console.log("watch,Location is logged");
              this.watch.unsubscribe();
            },(err)=>{
              console.log("Attendance service fails,Saves to localstorage");
              this.offlinePositions.push(this.positionArr);
              localStorage.setItem("offlinepositions",JSON.stringify(this.offlinePositions));
              this.watch.unsubscribe();
            });
          }else{
            console.log("Connection fails,Saves to localstorage");
            this.offlinePositions.push(this.positionArr);
            localStorage.setItem("offlinepositions",JSON.stringify(this.offlinePositions));
            this.watch.unsubscribe();
          }
        }else{
            this.attendService.loggingAfterLogout(this.positionArr).subscribe(location=>{
            console.log("After Logout,Location is logged");
            this.watch.unsubscribe();
          },(err)=>{
            console.log("Attendance service fails,Saves to localstorage");
            this.offlinePositions.push(this.positionArr);
            localStorage.setItem("offlinepositions",JSON.stringify(this.offlinePositions));
            this.watch.unsubscribe();
          });
        }
      },(err)=>{
        console.log("Background logging fails");
      });
    },30*60*1000);
  }
  
  openPage(page) {
    if(page.title=="Logout"){
      this.showConfirm(page);
      localStorage.setItem('isLoggedIn',"no");
    }else{
      this.nav.setRoot(page.component);
    }
  }

  showConfirm(page) {
    let confirm = this.alertCtrl.create({
      title: 'Logout?',
      message: 'Are you sure want to Logout?',
      buttons: [
        {
          text: 'No',
          handler: () => {
          }
        },
        {
          text: 'Yes',
          handler: () => {
            if(this.globalService.isConnected()){
              this.authService.logout().subscribe(x=>{
                this.app.getRootNav().setRoot(MainPage);
                this.userService.updateStatus('offline');
                this.userService.signout();
                localStorage.removeItem('currentUser');
              });
            }else{
              console.log("page",page);
              this.nav.setRoot(page.component);
            }
          }
        }
      ]
    });
    confirm.present();
  }
}
