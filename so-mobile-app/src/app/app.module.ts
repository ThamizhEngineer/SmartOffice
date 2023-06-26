import { NgModule, ErrorHandler, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { MyApp } from './app.component';
import { LoginPage } from './../pages/login/login';
import{LeaveApprovalPage} from '../pages/leave-approval/leave-approval';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { FilePath } from '@ionic-native/file-path';
import { CommonService } from './../providers/common.service';
import { AuthenticationService } from './../providers/authentication.service';
import { AttendanceService } from './../providers/attendance.service';
import { TasksService } from './../providers/tasks.service';
import { MySpaceService } from './../providers/my-space.service';
import{LeaveService} from '../services/leave.service';
import{ExpenseClaimService} from '.././services/expense-claim.service';
import{UserService} from '.././services/user.service';
import { AppSettings } from '../services/app-settings';
import { ToastService } from '../services/toast-service';
import { LoadingService } from '../services/loading-service';
import { AlertService } from './../services/alert.service';
import { Transfer } from '@ionic-native/transfer';
import { LoginPageModule } from '../pages/login/login.module';
import { Base64 } from '@ionic-native/base64';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { File } from '@ionic-native/file';
import { FileTransfer, FileTransferObject } from '@ionic-native/file-transfer';
import { FileOpener } from '@ionic-native/file-opener';
import{LeaveDetailPage} from '../pages/leave-detail/leave-detail.component';
import{ExpenseCreateClaimPage} from '../pages/expense-create-claim/expense-create-claim.component';
import{ExpenseStartPage} from '../pages/expense-start/expense-start.component';
import{ExpenseDetailPage} from '../pages/expense-detail/expense-detail.component';
@NgModule({
  declarations: [
    MyApp,
    LeaveDetailPage,
    ExpenseCreateClaimPage,
    ExpenseStartPage,
    ExpenseDetailPage
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp),
    AngularFireModule.initializeApp(AppSettings.FIREBASE_CONFIG),
    AngularFireDatabaseModule, AngularFireAuthModule, AngularFirestoreModule,
    LoginPageModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginPage,
    LeaveDetailPage,
    LeaveApprovalPage,
    ExpenseCreateClaimPage,
    ExpenseStartPage,
    ExpenseDetailPage
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    StatusBar,
    SplashScreen,
    File,
    FileTransfer,
    FilePath,
    Transfer,
    Base64,
    FileTransferObject,
    FileOpener,
    ToastService, 
    LoadingService,
    ExpenseClaimService,
    UserService,
    CommonService,
    AuthenticationService,
    AttendanceService,
    TasksService,
    LeaveService,
    MySpaceService,
    AlertService,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
