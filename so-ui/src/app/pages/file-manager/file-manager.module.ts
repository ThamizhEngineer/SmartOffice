import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap' ;

import { FileManagerService } from './file-manager.service';
import { FileManagerRoutes } from './file-manager.routing';
import { FileManagerComponent } from './_comp/file-manager.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        NgbModule,
        RouterModule.forChild(FileManagerRoutes)
    ],    
    declarations: [FileManagerComponent],
    providers: [FileManagerService],
})
export class FileManagerModule { }
