import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AttendancePage } from './attendance';

import { AgmCoreModule } from '@agm/core';
import { AppSettings } from './../../services/app-settings';

import { Geolocation } from '@ionic-native/geolocation';
import { LocationAccuracy } from '@ionic-native/location-accuracy';

@NgModule({
  declarations: [
    AttendancePage,
  ],
  imports: [
    IonicPageModule.forChild(AttendancePage),
    AgmCoreModule.forRoot(AppSettings.MAP_KEY)
  ],
  providers:[
    Geolocation,
    LocationAccuracy
  ]
})
export class AttendancePageModule {}
