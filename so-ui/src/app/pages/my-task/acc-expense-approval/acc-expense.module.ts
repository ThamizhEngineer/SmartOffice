import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ExpenseListComponent } from './comp/acc-expense-list.component';
import { ExpenseViewComponent } from './comp/acc-expense-view.component';
import { AccExpenseClaimRoutes } from './acc-expense.routing'
import { ExpenseService } from '../expense.service';

@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(AccExpenseClaimRoutes),
        SharedModule
    ],
    exports: [],
    declarations: [ExpenseListComponent,ExpenseViewComponent],
    providers: [ExpenseService],
})
export class AccExpenseModule { }
