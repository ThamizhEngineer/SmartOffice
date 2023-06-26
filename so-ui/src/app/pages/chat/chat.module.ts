import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms'; 
import { ReactiveFormsModule } from '@angular/forms';

import { ChatListComponent } from './_comp/chat-list.component';
import { ChatDetailComponent } from './_comp/chat-detail.component';
import { Chatroutes } from './chat.routing';
import { ChatService } from './chat.service';


@NgModule({
    imports: [CommonModule,NgbModule,RouterModule.forChild(Chatroutes),FormsModule,ReactiveFormsModule],
    exports: [],
    declarations: [ChatListComponent,ChatDetailComponent],
    providers: [ChatService],
})
export class ChatModule { }
