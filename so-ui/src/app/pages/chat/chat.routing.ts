import { Routes } from '@angular/router';
import { ChatListComponent } from './_comp/chat-list.component';
import { ChatDetailComponent } from './_comp/chat-detail.component';

export const Chatroutes: Routes = [
    { path: '', component: ChatListComponent },
    { path: ':id', component: ChatDetailComponent }

]