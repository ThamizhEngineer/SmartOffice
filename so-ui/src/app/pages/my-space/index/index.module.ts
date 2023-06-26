import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './comp/index.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { IndexnRoutes } from './index.routing';



@NgModule({
    imports: [RouterModule.forChild(IndexnRoutes),SharedModule,CommonModule,FormsModule,
                NgbModule],
    exports: [],
    declarations: [IndexComponent],
})
export class IndexModule { }