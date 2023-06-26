import { NgModule } from '@angular/core';

import { MyTarAcc2ApprovceComponent } from './comp/my-tar-acc2-approve-list.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MyTarAcc2ApproveRoutes } from './my-tar-acc2-approve.routing';
import { Acc2ApproveComponent } from './comp/my-tar-acc2.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
    imports: [
        CommonModule,NgxPaginationModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(MyTarAcc2ApproveRoutes),
        SharedModule
    ],
    exports: [],
    declarations: [MyTarAcc2ApprovceComponent,Acc2ApproveComponent],
    providers: [],
})
export class MyTarAcc2ApprovceModule { }
