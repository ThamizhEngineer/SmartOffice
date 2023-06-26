import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { CalendarEventsPage } from './calendar-events.component';

@NgModule({
  declarations: [
    CalendarEventsPage,
  ],
  imports: [
    IonicPageModule.forChild(CalendarEventsPage),
  ],
})
export class CalendarEventsPageModule {}
