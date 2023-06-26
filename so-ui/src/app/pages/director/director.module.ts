import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import {EmployeeService} from '../operation/employee/employee.service';
import { SharedModule } from './../../shared/shared.module';
import { TravelAdvanceListComponent} from './travel-advance/travel-advance-list.component';
import { DirectorRoutes } from './director.routing';
import { OnBoardProfileComponent } from './on-board-profile/_comp/on-board-profile.component';
import { MyTarRequestService} from'../my-space/my-tar-request/my-tar-request.service';
@NgModule({
    imports: [CommonModule,SharedModule,FormsModule,NgbModule,RouterModule.forChild(DirectorRoutes)],
    exports: [],
    declarations: [OnBoardProfileComponent, TravelAdvanceListComponent ],
    providers: [EmployeeService, MyTarRequestService ],
})
export class DirectorModule { }
