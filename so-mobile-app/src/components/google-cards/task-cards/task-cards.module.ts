import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { TaskCardsComponent } from './task-cards';

@NgModule({
    declarations: [
        TaskCardsComponent,
    ],
    imports: [
        IonicPageModule.forChild(TaskCardsComponent),
    ],
    exports: [
        TaskCardsComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class TaskCardsModule { }
