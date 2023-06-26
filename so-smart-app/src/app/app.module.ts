import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { UtilsService } from '../app/services/utils.service';
import { AuthenticationService } from '../app/services/authentication.service';
import { HttpClient } from '@angular/common/http';
import { NgApexchartsModule } from "ng-apexcharts";
import {  NO_ERRORS_SCHEMA } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { LocationAccuracy } from '@ionic-native/location-accuracy/ngx';
import { AndroidPermissions } from '@ionic-native/android-permissions/ngx';
import { NativeGeocoder } from '@ionic-native/native-geocoder/ngx';
import {Geolocation} from '@ionic-native/geolocation/ngx';
import { LogoutComponent } from './pages/login/logout.component';
import { LottieSplashScreen } from '@ionic-native/lottie-splash-screen/ngx';
import { HTTP } from '@ionic-native/http/ngx';
import { PhotoViewer } from '@ionic-native/photo-viewer/ngx';
import { Downloader } from '@ionic-native/downloader/ngx';
import { File } from '@ionic-native/file/ngx';
import { Camera } from '@ionic-native/camera/ngx';
import { ImagePicker } from '@ionic-native/image-picker/ngx';

@NgModule({
  declarations: [AppComponent,LogoutComponent],
  entryComponents: [],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule,HttpClientModule,NgApexchartsModule,FormsModule,ReactiveFormsModule],
  schemas: [
    NO_ERRORS_SCHEMA
  ],
  providers: [
    StatusBar,
    UtilsService,
    ImagePicker,
    AuthenticationService,
    Camera,
    SplashScreen,
    LottieSplashScreen,
    PhotoViewer,
    HttpClient,
    Downloader,
    File,
    LocationAccuracy,
    HTTP,
    HttpClient,
    Geolocation,
    NativeGeocoder,
    AndroidPermissions,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
