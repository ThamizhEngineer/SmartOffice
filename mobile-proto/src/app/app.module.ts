import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { Network } from '@ionic-native/network/ngx';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LogoutComponent } from './auth/_comp/logout.component';
import {Geolocation} from '@ionic-native/geolocation/ngx';
import { Camera } from '@ionic-native/camera/ngx';
import { Platform } from 'ionic-angular';
import { NativeGeocoder } from '@ionic-native/native-geocoder/ngx';
import { AttendanceService } from './services/attendance.service';
import { LocationAccuracy } from '@ionic-native/location-accuracy/ngx';
import { AndroidPermissions } from '@ionic-native/android-permissions/ngx';

@NgModule({
  declarations: [
    AppComponent,
    LogoutComponent
  ],
  entryComponents: [],
  imports: [
    BrowserModule, 
    IonicModule.forRoot(), 
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    StatusBar,
    SplashScreen,
    Network,
    LocationAccuracy,
    Camera,
    HttpClient,
    Geolocation,
    NativeGeocoder,
    AttendanceService,
    AndroidPermissions,
    Platform,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
//AIzaSyDdt-dwz_V7-4yTvBh2AXP8LqQkxb3EGmU