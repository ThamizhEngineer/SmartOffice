import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { Diagnostic } from '@ionic-native/diagnostic';
import { Network } from '@ionic-native/network';

import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { config } from './app.firebaseconfig';

import { AppComponent } from './app.component';
import { MainPage } from './../pages/main/main.component';

import { MainPageModule } from './../pages/main/main.module';

import { AuthenticationService } from "./../services/authentication.service";
import { AttendanceService } from './../services/attendance.service';
import { GlobalService } from '../services/global.service';
import { AlertService } from '../services/alert.service';
import { UserService } from '../services/user.service';
import { ChatService } from '../services/chat.service';
import { GroupsService } from '../services/groups.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(AppComponent),
    AngularFireModule.initializeApp(config),
    AngularFireAuthModule,
    AngularFireDatabaseModule,
    MainPageModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    AppComponent,
    MainPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    Diagnostic,
    Network,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AuthenticationService,
    AttendanceService,
    GlobalService,
    AlertService,
    UserService,
    ChatService,
    GroupsService
  ]
})
export class AppModule {}
