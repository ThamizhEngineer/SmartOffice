import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FileModule } from './../../../shared/file.module';
import { SharedModule } from './../../../shared/shared.module';
import { ExpenseApprovalComponent } from '../expense-approval/_comp/expense-approval.component';
import { ExpenseClaimService } from '../../my-space/expense-claim/expense-claim.service';
import { ExpenseApprovalViewComponent } from '../expense-approval/_comp/expense-approval-view.component';
import { ExpenseClaimCreateComponent } from './_comp/expense-claim-create.component';
import { ExpenseApprovalRoutes } from './expense-approval.routing';
import { ExpenseApprovalService } from './../expense-approval/expense-approval.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule,SharedModule,FormsModule,FileModule,
        RouterModule.forChild(ExpenseApprovalRoutes),
    ],
    declarations: [ExpenseApprovalComponent,ExpenseApprovalViewComponent,ExpenseClaimCreateComponent],
    providers:[ExpenseApprovalService,ExpenseClaimService]
})

export class ExpenseApprovalModule { }
