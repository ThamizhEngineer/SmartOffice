import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{AddTestParticipantsDetailComponent} from '../add-test-participants/comp/add-test-participants-detail.component';
import{AddTestParticipantsListComponent} from '../add-test-participants/comp/add-test-participants-list.component';
import{AddTestParticipantsRoutes} from '../add-test-participants/add-test-participants.routing';
import{CommonService} from '../../../shared/common/common.service';


@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,SharedModule,
        RouterModule.forChild(AddTestParticipantsRoutes),
    ],
    providers:[CommonService],
    declarations: [AddTestParticipantsDetailComponent,AddTestParticipantsListComponent]
})

export class AddTestParticipantsModule { }