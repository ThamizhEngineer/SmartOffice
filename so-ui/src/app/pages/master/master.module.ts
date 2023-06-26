import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MasterRoutes } from './master.routing';
import { MasterComponent } from './_comp/master.component';

import { TypeaheadPopupDirective } from './popup.directive';


@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,
    RouterModule.forChild(MasterRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
    })
  ],

  declarations: [
    MasterComponent ,TypeaheadPopupDirective
  ]
})
export class MasterModule { }
