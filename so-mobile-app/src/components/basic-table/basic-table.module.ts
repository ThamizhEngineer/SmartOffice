import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BasicTableComponent } from './basic-table';

@NgModule({
    declarations: [
        BasicTableComponent,
    ],
    imports: [
        IonicPageModule.forChild(BasicTableComponent),
    ],
    exports: [
        BasicTableComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class BasicTableModule { }
