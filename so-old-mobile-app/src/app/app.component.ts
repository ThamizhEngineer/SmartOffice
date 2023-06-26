import { Component} from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen'

import { MainPage } from '../pages/main/main.component';

import { AlertService } from './../services/alert.service';

@Component({
  templateUrl: 'app.component.html'
})
export class AppComponent {

  rootPage:any = MainPage;
 
  constructor(public platform: Platform, 
              public statusBar: StatusBar, 
              public splashScreen: SplashScreen,
              public alertService: AlertService) {
    this.initializeApp(); 
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.show();
      this.splashScreen.hide();
    });
  }
}
 

  



