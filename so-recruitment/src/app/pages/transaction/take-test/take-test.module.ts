import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { TakeTestRoutes } from '../take-test/take-test.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { QuestionsQAComponent } from '../take-test/comp/qa.component';
import{TestMarkComponent} from '../take-test/comp/test-mark.component';
import{TakeTestListComponent} from '../take-test/comp/take-test-list.component';
import{TestAcknowledgementComponent} from '../take-test/comp/test-acknowledgement';
import{AddTestParticipantsService} from '../../knowledge-assessment/add-test-participants/add-test-participants.service';
import { CommonService } from '../../../shared/common/common.service';
import { FormsModule } from '@angular/forms';
import { FileModule } from './../../../shared/file.module';
import { IncidentTestService } from './incident-test.service';
@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(TakeTestRoutes),NgbTooltipModule
    ],
    providers: [ AddTestParticipantsService, CommonService,IncidentTestService],
    declarations: [QuestionsQAComponent,TestMarkComponent,TakeTestListComponent,TestAcknowledgementComponent]
})

export class TakeTestModule  { }