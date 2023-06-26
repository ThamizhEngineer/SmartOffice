import { Component } from '@angular/core';

import { Platform, Events } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { Network } from '@ionic-native/network/ngx';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {
  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private network: Network,
    private events: Events,
    private commonService: CommonService
  ) {
    this.initializeApp();
    // this.platform.backButton.subscribeWithPriority(0, () => {
    //   // code that is executed when the user pressed the back button
    //   // and ionic doesn't already know what to do (close modals etc...)
    // })
    
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.backgroundColorByHexString('#0973ba');
      this.splashScreen.hide();
      this.commonService.initializeNetworkEvents();

      this.platform.backButton.subscribeWithPriority(0, () => {
  // code that is executed when the user pressed the back button
  // and ionic doesn't already know what to do (close modals etc...)
})

      // Offline event
      this.events.subscribe('network:offline', () => {
        console.log('network:offline ==> ',this.network.type);    
      });

      // Online event
      this.events.subscribe('network:online', () => {
        console.log('network:online ==> ',this.network.type);        
      });
    });
  }

 
}
