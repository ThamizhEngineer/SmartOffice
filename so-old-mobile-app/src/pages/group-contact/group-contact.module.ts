import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { GroupContactPage } from './group-contact.component';

@NgModule({
  declarations: [
    GroupContactPage,
  ],
  imports: [
    IonicPageModule.forChild(GroupContactPage),
  ],
})
export class GroupContactPageModule {}
