import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BasicCardsComponent } from './basic-cards';

@NgModule({
    declarations: [
        BasicCardsComponent,
    ],
    imports: [
        IonicPageModule.forChild(BasicCardsComponent),
    ],
    exports: [
        BasicCardsComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class BasicCardsModule { }
