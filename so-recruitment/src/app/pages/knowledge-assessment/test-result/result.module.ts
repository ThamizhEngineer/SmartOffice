import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{ TestResultListComponent } from '../test-result/_comp/test-result-list.component';
import{TestResultRoutes} from '../test-result/result.routing';
import{CommonService} from '../../../shared/common/common.service';


@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,SharedModule,
        RouterModule.forChild(TestResultRoutes),
    ],
    providers:[CommonService],
    declarations: [TestResultListComponent]
})

export class TestResultModule { }