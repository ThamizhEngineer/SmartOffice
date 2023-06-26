import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CalendarPage } from './calendar.component';

import { Calendar } from '@ionic-native/calendar';

import { CalendarEventsPageModule } from './../calendar-events/calendar-events.module';

@NgModule({
  declarations: [
    CalendarPage,
  ],
  imports: [
    IonicPageModule.forChild(CalendarPage),
    CalendarEventsPageModule
  ],
  providers: [
    Calendar
  ]
})
export class CalendarPageModule {}
