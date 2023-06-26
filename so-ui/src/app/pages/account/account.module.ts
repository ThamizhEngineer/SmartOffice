import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MyTarRequestService } from '../my-task/my-tar.service';
import { SharedModule } from './../../shared/shared.module';




const routes: Routes = [
    { path: 'my-tar-acc2', loadChildren: '../my-task/my-tar-acc2-approve/my-tar-acc2-approve.module#MyTarAcc2ApprovceModule' },
    { path: 'acc-expense', loadChildren: '../my-task/acc-expense-approval/acc-expense.module#AccExpenseModule' },
    { path: 'cash-balance', loadChildren: '../operation/cash-balance/cash-balance.module#CashBalanceModule' },
    { path: 'expense-approval', loadChildren: '../operation/expense-approval/expense-approval.module#ExpenseApprovalModule' },
    { path: 'bank-advise-report', loadChildren: '../report/bank-advise-report/bank-advise-report.module#BankAdviseReportModule' },
    { path: 'exit-interview', loadChildren: '../operation/exit-interview/exit-interview.module#ExitInterviewModule' },
]

@NgModule({
    imports: [CommonModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [],
    providers: [MyTarRequestService]
})
export class AccountModule { }