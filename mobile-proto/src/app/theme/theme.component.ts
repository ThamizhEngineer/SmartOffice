import { Component, OnInit, ViewChild } from '@angular/core';
import { AlertController, IonTabs } from '@ionic/angular';
import { CommonService } from '../services/common.service';
import { AuthenticationService } from '../services/authentication.service';

import { Router } from '@angular/router';
import { AppVersion } from '@ionic-native/app-version/ngx';

export interface PageInterface {
  title: string;
  tabComponent?: any;
  index?: number;
  visible:boolean;
  icon: string;
  url:string;
  subs:any;
  notifications?: any;
}

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss'],
})
export class ThemeComponent implements OnInit {

  pages: PageInterface[];
  username:string ="Ionic Theme";
  name: string = "Smart Office";
  email:string ="";
  role:string ="";
  page: any;
  data: any = [];
  appVer: string;
  showLevel1 = null;
  empaData:any;
  value:any;
  @ViewChild(IonTabs) tabs:IonTabs;


  constructor(private router: Router,
              private alertCtrl: AlertController, 
              private commonService: CommonService,
              private appVersion: AppVersion,
              private authenticationService: AuthenticationService) { 
      this.pages = [
        { title: 'Dashboard', icon: 'fas fa-lg fa-home m-r-16', url:'/dashboard' , visible: true ,subs:null },
        { title: 'Notifications', icon: 'fas fa-lg fa-list m-r-16', url:'/notif' , visible: true ,subs:null, notifications: "3"  },
        { title: 'Chat', icon: 'fas fa-lg fa-comment m-r-16', url:'/help' , visible: true ,subs:null },

      /*  { title: 'Tutorial'		, icon: 'fas fa-lg fa-book m-r-16', url:'/tutorial'      , visible: true ,subs:null},
        { title: 'Button'		, icon: 'fas fa-lg fa-circle m-r-16', url:'/button'      , visible: true ,subs:null },
        { title: 'Forms'		, icon: 'fas fa-lg fa-keyboard m-r-16', url:'/forms'      , visible: true ,subs:null },
        { title: 'List'		, icon: 'fas fa-lg fa-list m-r-16', url:'/', visible: true, subs :[{ title: 'Normal', icon: 'fas fa-lg fa-home m-r-16', url:'/normal-list', visible: true },
        { title: 'Avatar List', icon: 'fas fa-lg fa-home m-r-16', url:'/avatar-list', visible: true },{ title: 'Card List', icon: 'fas fa-lg fa-home m-r-16', url:'/card-list', visible: true }]},
        { title: 'Cart'		, icon: 'fas fa-lg  fa-shopping-cart m-r-16', url:'/shopping-cart'      , visible: true ,subs:null },
        { title: 'Accordion'		, icon: 'fas fa-lg  fa-minus-circle m-r-16', url:'/accordion' , visible: true ,subs:null },
        // { title: 'Calendar'		, icon: 'fas fa-lg  fa-map m-r-16', url:'/calendar' , visible: true ,subs:null },
        { title: 'Map'		, icon: 'fas fa-lg  fa-map-marker m-r-16', url:'/map' , visible: true ,subs:null },*/
        { title: 'Log Out'	, icon: 'fas fa-lg fa-sign-out-alt m-r-16'  , url:'/logout'    , visible: true,subs:null}
      ];

      this.data = [
        { page: 'dashboard' , icon: "home" , title: "Home"  , visible: true, },
        { page: 'dashboard' , icon: "hourglass" , title: "Jobs"  , visible: true, },
        { page: 'dashboard' , icon: "paper" , title: "Tasks"  , visible: true, },
        { page: 'notif' , icon: "notifications" , title: "Notifications"  , visible: true, },
        { page: 'dashboard' , icon: "chatboxes" , title: "Chat" , visible: true }
      ];
    }

  ngOnInit() {
    this.appVersion.getVersionNumber().then(x=>{
      this.appVer = x;
    });
    this.empaData=this.authenticationService.getData();
    this.value=this.empaData.value;
    console.log(this.empaData)
    console.log(this.value)
    this.email=this.value.emailId;
    this.role=this.value.empDesignation;


  }

  navigateTo(page){
    if(page.title==="Log Out"){
      this.showLogoutPopup();
    }else{
      this.router.navigate([page.url]);
    }
  }
  isLevel1Shown(idx) {
    return this.showLevel1 === idx;
  };
  toggleLevel1(idx) {
    if (this.isLevel1Shown(idx)) {
      this.showLevel1 = null;
    } else {
      this.showLevel1 = idx;
    }
  };

  async showLogoutPopup(){
    const confirm = await this.alertCtrl.create({
       header: 'Logout?',
       message: '<strong>Are you sure want to Logout?</strong>',
       buttons: [
         {
           text: 'Cancel',
           role: 'cancel',
           cssClass: 'secondary',
           handler: () => {
           }
         },
         {
           text: 'Okay',
           handler: () => {
            this.router.navigate(['/logout']);
           }
         }
       ]
     });
     await confirm.present();
    }

    doSomething() {
      console.log("myTabs",123);
  }

}
