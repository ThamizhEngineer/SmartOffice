import { NgModule } from '@angular/core';

import { MyTarN1ApproveComponent } from './comp/my-tar-n1-approve-list.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MyTarN1ApproveRoutes } from './my-tar-n1-approve.routing';
import { N1ApproveComponent } from './comp/my-tar-n1-approve.component';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
    imports: [
        CommonModule,NgxPaginationModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(MyTarN1ApproveRoutes),
        SharedModule
    ],
    exports: [],
    declarations: [MyTarN1ApproveComponent,N1ApproveComponent],
    providers: [],
})
export class MyTarN1ApproveModule { }
