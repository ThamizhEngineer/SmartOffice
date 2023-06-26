import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';
import { ChatService } from "./service/chat-service";
import { RelativeTime } from "./pipes/relative-time";
import { EmojiPickerComponent} from "../../components/emoji-picker/emoji-picker";
import { EmojiProvider } from "./service/emoji";
import { ChatPage } from './chat.page';
import { ComponentsModule } from 'src/app/components/components.module';
const routes: Routes = [
  {
    path: '',
    component: ChatPage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    ComponentsModule
  ],
  declarations: [ChatPage,EmojiPickerComponent,
    RelativeTime],
  providers: [
    ChatService,
    EmojiProvider
  ]
})
export class ChatPageModule {}
