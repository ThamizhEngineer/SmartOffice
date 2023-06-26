import { FlagComponent } from './_models/flag.component';
import { DisplayAlertComponent } from './_models/alert-display.component';
import { ConfirmDeleteComponent } from './_models/confirm.component';
import { FileUploadComponent } from './_models/file.upload.component';
import { SODatePickerComponent } from './_models/date.picker';
import { StarFillPipe } from './_models/star.fill';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MonthPicker } from './_models/month.picker';

@NgModule({
    declarations: [
        StarFillPipe, FlagComponent, DisplayAlertComponent, ConfirmDeleteComponent, MonthPicker, SODatePickerComponent
    ],
	imports: [
        CommonModule, NgbModule, RouterModule, FormsModule
    ],
	exports: [
        StarFillPipe, FlagComponent, DisplayAlertComponent, ConfirmDeleteComponent, MonthPicker, SODatePickerComponent
    ],
})
export class SharedModule { }