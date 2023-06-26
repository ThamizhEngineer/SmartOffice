import { FlagComponent } from './_models/flag.component';
import { DisplayAlertComponent } from './_models/alert-display.component';
import { ConfirmDeleteComponent } from './_models/confirm.component';
import { FileUploadComponent } from './_models/file.upload.component';
import { SODatePickerComponent } from './_models/date.picker';
import { StarFillPipe } from './_models/star.fill';
import { HttpClientModule } from '@angular/common/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ApplicantDetailEditComponent } from './_models/applicant-detail-edit.component';
import { MapComponent } from './_models/map.component';
import { MonthPicker } from './_models/month.picker';
import { AutoCompeleteComponent } from './_models/autocomplete.component';
import { KnownLocationComponent } from './_models/known-location.component';
import { ProgressProcessorComponent } from './_models/progress-processor.component';
import { SaleOrderModelComponent } from './_models/sale-order-module/sale-order-model.component';
import { JobStatusModelComponent } from './_models/job-status/job-status-model.component';
import { TypeaheadPopupDirective } from './directives/popup.directive';
import { LinebreaksPipe } from './_models/linebreak.component';
import { PreviewDocumentComponent } from './_models/preview-document.component';
import { NgxDocViewerModule } from 'ngx-doc-viewer'; 
import {  PdfViewerModule } from 'ng2-pdf-viewer';
import { DownloadDocumentComponent } from './_models/download-document.component';

@NgModule({
    declarations: [
        StarFillPipe, LinebreaksPipe, MapComponent, ApplicantDetailEditComponent, SaleOrderModelComponent, JobStatusModelComponent, TypeaheadPopupDirective, FlagComponent, KnownLocationComponent, ProgressProcessorComponent, AutoCompeleteComponent, DisplayAlertComponent, ConfirmDeleteComponent, MonthPicker, SODatePickerComponent, PreviewDocumentComponent, DownloadDocumentComponent
    ],
    imports: [
        CommonModule, NgbModule, RouterModule, FormsModule, HttpClientModule, ReactiveFormsModule, NgxDocViewerModule, PdfViewerModule
    ],
    exports: [
        StarFillPipe, LinebreaksPipe, FlagComponent, MapComponent, SaleOrderModelComponent, JobStatusModelComponent, TypeaheadPopupDirective, ApplicantDetailEditComponent, KnownLocationComponent, ProgressProcessorComponent, AutoCompeleteComponent, DisplayAlertComponent, ConfirmDeleteComponent, MonthPicker, SODatePickerComponent, PreviewDocumentComponent, DownloadDocumentComponent
    ],
    entryComponents: [SaleOrderModelComponent, JobStatusModelComponent] 
})
export class SharedModule { }

