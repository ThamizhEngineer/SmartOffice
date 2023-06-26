import { NgModule } from '@angular/core';

import { MyTarRequestListComponent } from './comp/my-tar-request-list.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MyTarRequestRoutes } from './my-tar-request.routing';
import { MyTarRequestComponent } from './comp/my-tar-request.component';
import { MyTarRequestService } from './my-tar-request.service';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
    imports: [
        CommonModule,NgxPaginationModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(MyTarRequestRoutes),
        SharedModule
    ],
    exports: [],
    declarations: [MyTarRequestListComponent,MyTarRequestComponent],
    providers: [MyTarRequestService],
})
export class MyTarRequestModule { }
