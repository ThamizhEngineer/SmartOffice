
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MapLocationComponent} from '../shared/_models/map-location.component'


@NgModule({
    declarations: [ MapLocationComponent ],
	imports: [
        CommonModule, RouterModule, FormsModule
    ],	
	exports: [ MapLocationComponent ],
})
export class MapLocationModule { }