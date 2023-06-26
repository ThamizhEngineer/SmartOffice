import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import{AddTestParticipantsService} from '../knowledge-assessment/add-test-participants/add-test-participants.service';
import { TransactionRoutes } from './transaction.routing';
import { TransactionComponent } from './transaction.component';
// import{QuestionsService} from '../knowledge-assessment/questions/questions.service';
import { CommonService} from './../../shared/common/common.service';
// import { SaleOrderService} from'./sale-order/sale-order.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(TransactionRoutes)
  ],
  providers:[CommonService,AddTestParticipantsService],
  declarations: [ TransactionComponent ]
})
export class TransactionModule { }
