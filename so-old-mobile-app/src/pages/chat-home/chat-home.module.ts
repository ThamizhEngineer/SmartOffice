import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ChatHomePage } from './chat-home.component';

import { SearchPageModule } from './../search/search.module';
import { SingleChatPageModule } from './../single-chat/single-chat.module';
import { GroupChatPageModule } from './../group-chat/group-chat.module';
import { CreateGroupPageModule } from './../create-group/create-group.module';
import { GroupContactPageModule } from './../group-contact/group-contact.module';

@NgModule({
  declarations: [
    ChatHomePage
  ],
  imports: [
    IonicPageModule.forChild(ChatHomePage),
    SearchPageModule,
    SingleChatPageModule,
    GroupChatPageModule,
    CreateGroupPageModule,
    GroupContactPageModule
  ],
})
export class ChatHomePageModule {}
