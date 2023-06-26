import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FileModule } from '../../../shared/file.module';
import { OfficeMasterComponent } from './comp/office-master.component';
import { OfficeMasterService } from './office-master.service';

const routes: Routes = [
    {path:"",component:OfficeMasterComponent}
]

@NgModule({
    imports: [CommonModule, NgbModule, FormsModule, ReactiveFormsModule, SharedModule, FileModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [OfficeMasterComponent],
    providers: [OfficeMasterService],
})
export class OfficeMasterModule { }
