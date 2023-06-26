import { Component } from '@angular/core';
import { Network } from '@ionic-native/network';
import { AndroidPermissions } from '@ionic-native/android-permissions';
import { NavController, ToastController } from 'ionic-angular';

import { Subscription } from 'rxjs/Subscription';

import { HomePage } from '../home/home.component';

import { GlobalService } from './../../services/global.service';
import { AlertService } from './../../services/alert.service';
import { AuthenticationService } from "./../../services/authentication.service";
import { AttendanceService } from './../../services/attendance.service';
import { UserService } from './../../services/user.service';


@Component({
  selector: 'page-login',
  templateUrl: 'login.component.html'
})
export class LoginPage{
  model: any={};
  user: any={};
  currentTime: string=null;
  tokenValidityTime: string=null;
  connected: Subscription;
  disconnected: Subscription;
  newUser:boolean;
  firebaseUsers: any=null;
  uid: string;
  public positionArr: any = {'latitude': null, 'longitude':null};

  constructor(public navCtrl: NavController, 
              public toastCtrl: ToastController,
              public permission: AndroidPermissions,
              public authService: AuthenticationService,
              public attendService: AttendanceService,
              public globalService: GlobalService,
              public alertService: AlertService,
              public userService: UserService,
              public network: Network) { 
  
               }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
    this.androidPermission();
    this.logoutlocationLogging();
   
   
  }

  searchUser(email){
    this.userService.getUser(email).then((response)=>{
      if(response) {
        console.log("res",response);
        this.newUser=false;
      }else{
        this.newUser=true;
      }
    });
  }

  allFirebaseUsers(){
    this.userService.getAllUsers().then((response)=>{
      if(response){
        console.log("res1",response);
        this.firebaseUsers=response;
      }
    });
  }

  logoutlocationLogging(){
    if(!this.globalService.isLoggedIn() && this.globalService.isConnected() && this.positionArr.latitude){
      setInterval(()=>{
        this.attendService.logging(this.positionArr).subscribe(x=>{
          console.log("After Logout,Location is logged in ");
        },(err)=>{
          console.log("logout location failed");
        });
      },1800000);
    }
  }

  androidPermission(){
    this.permission.requestPermissions([this.permission.PERMISSION.ACCESS_COARSE_LOCATION,
                                          this.permission.PERMISSION.ACCESS_FINE_LOCATION]);
  }

  login(){
    if(this.network.type != 'none'){
      localStorage.setItem("isConnected","yes");
      this.authService.login(this.model.email,this.model.password)
        .subscribe(
          (data)=>{
            let currentUser=JSON.parse(localStorage.getItem('currentUser'));
            let firebaseData={
              "email":currentUser.emailId,
              "password":this.model.password,
              "nickname":currentUser.firstName
            }
            this.userService.getUser(firebaseData.email).then((response:any)=>{
              if(!response.email){
                this.userService.addUser(firebaseData).then((res)=>{
                  if(res){
                    console.log("User added to firebase");
                  }else{
                    console.log("Adding user to firebase failed");
                  }
                })
              }else{
                this.uid=response.uid;
                localStorage.setItem('uid',this.uid);
                this.userService.loginUser(firebaseData).then((res)=>{
                  console.log("User loggedIn",res);
                  this.userService.updateStatus('online');
                })
              }
            });
            this.navCtrl.push(HomePage);
          },
          error=>{
            this.alertService.presentAlert('Invalid Username/Password');
          });
    }else{
      this.alertService.presentAlert('Please check the Internet connection');
    }
  }

  

  ionViewCanEnter() {
    this.user=JSON.parse(localStorage.getItem('currentUser'));

    this.connected= this.network.onConnect().subscribe(data=>{
      this.networkUpdate(data.type);
      localStorage.setItem("isConnected","yes");
    },error=>{
 
    });
 
    this.disconnected=this.network.onDisconnect().subscribe(data=>{
     this.networkUpdate(data.type);
     localStorage.setItem("isConnected","no");
    },error=>{
 
    });

    if(this.user){
      this.currentTime=this.user.modifiedDt;
      this.tokenValidityTime=this.user.tokenValidityDt;
      if(Date.parse(this.currentTime)< Date.parse(this.tokenValidityTime)){
        this.authService.validate().subscribe(x=>{
          this.userService.updateStatus('online');
          this.navCtrl.push(HomePage);
        },
        error=>{
          localStorage.removeItem('currentUser');
          this.navCtrl.push(LoginPage);
        });
      }
    }
  }

  networkUpdate(connectionState: string){
    this.toastCtrl.create({
      message:'You are now '+connectionState+' via '+this.network.type,
      duration:3000
    }).present();
  }
}
