import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import{KnowledgeAssessmentRoutes} from './knowledge-assessment.routing';
import{AddTestParticipantsService} from '../knowledge-assessment/add-test-participants/add-test-participants.service';
import{ TestResulService } from '../knowledge-assessment/test-result/result.service'
import{QuestionsService} from './questions/questions.service';
import{CommonService} from '../../shared/common/common.service';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(KnowledgeAssessmentRoutes)
  ],
providers:[QuestionsService,CommonService,AddTestParticipantsService,TestResulService],
  declarations: [ ]
})
export class KnowledgeAssessmentModule { }