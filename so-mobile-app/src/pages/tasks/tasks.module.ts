import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { TasksPage } from './tasks';

import { TaskCardsModule } from './../../components/google-cards/task-cards/task-cards.module';

@NgModule({
  declarations: [
    TasksPage,
  ],
  imports: [
    IonicPageModule.forChild(TasksPage),
    TaskCardsModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TasksPageModule {}
