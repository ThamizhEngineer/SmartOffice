import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{CreateTestDetailComponent} from '../create-test/comp/create-test-detail.component';
import{CreateTestListComponent} from '../create-test/comp/create-test-list.component';
import{CreateTestRoutes} from '../create-test/create-test.routing';
import{CommonService} from '../../../shared/common/common.service';


@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,SharedModule,
        RouterModule.forChild(CreateTestRoutes),
    ],
    providers:[CommonService],
    declarations: [CreateTestDetailComponent,CreateTestListComponent]
})

export class CreateTestModule { }