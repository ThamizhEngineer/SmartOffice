import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { TarService } from './tar.service';

import { TarListPage } from './_comp/list.page';
import { TarDetailPage } from './_comp/detail.page';
import { TarCreatePage } from './_comp/create.page';
import { ComponentsModule } from 'src/app/components/components.module';
import { Ionic4DatepickerModule } from '@logisticinfotech/ionic4-datepicker';

const routes: Routes = [
  { path: '', component: TarListPage },
  { path: 'create', component: TarCreatePage },
  { path: ':id', component: TarDetailPage }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule,Ionic4DatepickerModule
  ],
  declarations: [
    TarListPage, TarDetailPage, TarCreatePage
  ],
  providers:[ TarService ]
})
export class TarModule {}
