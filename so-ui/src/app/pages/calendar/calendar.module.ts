import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { CalendarRoutes } from './calendar.routing';
import { CalendarFullComponent } from './_comp/full.component';

import { CalendarService } from './calendar.service';

import { FullCalendarModule } from 'ng-fullcalendar';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,
    RouterModule.forChild(CalendarRoutes),
	FullCalendarModule
  ],
  providers:[CalendarService],
  declarations: [ 
    CalendarFullComponent
  ]
})
export class CalendarViewModule { }
