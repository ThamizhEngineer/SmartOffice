import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { SettingsRoutes } from './settings.routing';
import { SettingsDetailComponent } from './_comp/detail.component';

import { SettingsService } from './settings.service';

import { SharedModule } from './../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule, SharedModule, 
    RouterModule.forChild(SettingsRoutes)
  ],
  providers:[SettingsService],
  declarations: [ 
    SettingsDetailComponent
  ]
})
export class SettingsModule { }
