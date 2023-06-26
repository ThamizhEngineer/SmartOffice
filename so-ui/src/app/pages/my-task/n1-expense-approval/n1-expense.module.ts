import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { N1ExpenseListComponent } from './comp/n1-expense-list.component';
import { ExpenseViewComponent } from './comp/n1-expense-view.component';
import { N1ExpenseClaimRoutes } from './n1-expense.routing'
import { ExpenseService } from '../expense.service';

@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(N1ExpenseClaimRoutes),
        SharedModule
    ],
    exports: [],
    declarations: [N1ExpenseListComponent,ExpenseViewComponent],
    providers: [ExpenseService],
})
export class N1ExpenseModule { }
