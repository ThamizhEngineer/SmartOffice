import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { TarListPage } from './tar-list/tar-list.page';
import { TarDetailPage } from './tar-detail/tar-detail.page';
import { TarService } from './tar.service';
import {  TarCreatePage } from  './tar-create/tar-create.page';
import { UtilsService } from '../../services/utils.service'

const routes: Routes = [
  {
    path: '',component:TarListPage
  },
  {
    path: 'create',component:TarCreatePage
  },
  {
    path: ':id',component:TarDetailPage
  }
];

@NgModule({
  declarations: [TarListPage,TarDetailPage,TarCreatePage],
  imports: [
    CommonModule,RouterModule.forChild(routes),IonicModule,FormsModule
  ],
  exports: [RouterModule],
  providers:[TarService,UtilsService]
})
export class TarModule { }
