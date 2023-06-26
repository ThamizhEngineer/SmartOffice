import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChatListPage } from './chat-list/chat-list.page';
import { ChatDetailPage } from './chat-detail/chat-detail.page';

const routes: Routes = [
  {
    path: '',
    component: ChatListPage
  },
  {
    path: ':id/:type',component:ChatDetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ChatPageRoutingModule {}
