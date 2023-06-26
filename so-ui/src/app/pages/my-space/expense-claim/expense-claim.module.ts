import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from './../../../shared/shared.module';
import { FileModule } from './../../../shared/file.module';
import { ExpenseClaimListComponent } from '../expense-claim/_comp/expense-claim-list.component';
import { ExpenseClaimDetailComponent } from '../expense-claim/_comp/expense-claim-detail.component';
import { ExpenseClaimRoutes } from './expense-claim.routing';
import { ExpenseClaimService } from './expense-claim.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { DateParserService } from '../../../shared/_services/date-parser.service';

@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(ExpenseClaimRoutes),
        SharedModule
    ],
    declarations: [ExpenseClaimListComponent,ExpenseClaimDetailComponent],
    providers: [ ExpenseClaimService, DateParserService,DatePipe ]
})

export class ExpenseClaimModule { }
