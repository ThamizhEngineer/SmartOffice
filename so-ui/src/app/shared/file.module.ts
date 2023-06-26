import { FileUploadComponent } from './_models/file.upload.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';

@NgModule({
    declarations: [ FileUploadComponent ],
	imports: [
        CommonModule, RouterModule, FormsModule, HttpClientModule
    ],	
	exports: [ FileUploadComponent ],
})
export class FileModule { }