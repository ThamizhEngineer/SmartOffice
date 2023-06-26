import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { GroupChatPage } from './group-chat.component';

@NgModule({
  declarations: [
    GroupChatPage,
  ],
  imports: [
    IonicPageModule.forChild(GroupChatPage),
  ],
})
export class GroupChatPageModule {}
