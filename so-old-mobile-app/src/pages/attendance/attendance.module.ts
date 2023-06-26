import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AttendancePage } from './attendance.component';

import { Geolocation } from '@ionic-native/geolocation';
import { LocationAccuracy } from '@ionic-native/location-accuracy';

import { AgmCoreModule } from '@agm/core';
import { PipesModule } from './../../pipes/pipes.module';

@NgModule({
  declarations: [
    AttendancePage
  ],
  imports: [
    IonicPageModule.forChild(AttendancePage),
    AgmCoreModule.forRoot({
      apiKey:'AIzaSyBtE25u89FjClRLc3Xb5iDkntS8N1xFr5k'
    }),
    PipesModule
  ],
  providers:[
    LocationAccuracy,
    Geolocation
  ]
})
export class AttendancePageModule {}
