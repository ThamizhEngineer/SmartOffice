import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import{KnowledgeAssessmentRoutes} from './knowledge-assessment.routing';

import{QuestionsService} from './questions/questions.service';
import{CommonService} from '../../shared/common/common.service';


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(KnowledgeAssessmentRoutes)
  ],
providers:[QuestionsService,CommonService],
  declarations: [ ]
})
export class KnowledgeAssessmentModule { }