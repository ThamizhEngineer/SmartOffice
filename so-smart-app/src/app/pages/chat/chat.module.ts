import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ChatPageRoutingModule } from './chat-routing.module';

import { ChatListPage } from './chat-list/chat-list.page';
import { ChatDetailPage } from './chat-detail/chat-detail.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ChatPageRoutingModule
  ],
  declarations: [ChatListPage,ChatDetailPage]
})
export class ChatPageModule {}
