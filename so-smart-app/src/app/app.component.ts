import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { LottieSplashScreen } from '@ionic-native/lottie-splash-screen/ngx';
import { MenuController } from '@ionic/angular';
import { UtilsService } from './services/utils.service';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent {
  firstName = "Not found";
  desig = "Not found";
  menuItems:any = [];

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private lottieSplashScreen: LottieSplashScreen,
    private _utils: UtilsService,
    private menu: MenuController,
    private router: Router
  ) {
    this.initializeApp();    
    // this.event.subscribe('currentUser', (data) => {
    this._utils.getCurrentUserChange().subscribe(data => {   
      console.log("event", data)
      if (data.firstName) { this.firstName = data.firstName; } else { this.firstName = "Provide name" }
      if (data.empDesignation) { this.desig = data.empDesignation; } else { this.desig = "Designation not assigned" }
    });

    this._utils.getMenuItems().subscribe(x => {
      console.log(x)
      this.menuItems=x;
    }, error => {
      console.log(error)
    });

  }

  initializeApp() {
    this.platform.ready().then(() => {
      setTimeout(() => {
        this.lottieSplashScreen.hide()
      }, 4000);
      this.statusBar.styleDefault();
      // this.splashScreen.hide();
    });
  }

  menuControl(path) {
    this.router.navigate([path]);
    this.menu.close()
  }
}
