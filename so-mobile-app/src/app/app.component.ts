import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { LoginPage } from '../pages/login/login';

import { AppSettings } from '../services/app-settings';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
 
  rootPage:any = LoginPage;
 
  constructor(public platform: Platform, 
              public statusBar: StatusBar, 
              public splashScreen: SplashScreen) {
    this.initializeApp(); 
   
    if (AppSettings.SHOW_START_WIZARD) {
      
    }
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.show();
      this.statusBar.backgroundColorByHexString('#339961');
      this.splashScreen.hide();
    });
  }
}
