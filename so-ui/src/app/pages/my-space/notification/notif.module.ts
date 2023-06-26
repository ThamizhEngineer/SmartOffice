import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotificationComponent } from './comp/notif.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NotificationRoutes } from './notif.routing';



@NgModule({
    imports: [RouterModule.forChild(NotificationRoutes),SharedModule,CommonModule,FormsModule,
                NgbModule],
    exports: [],
    declarations: [NotificationComponent],
})
export class NotificationModule { }