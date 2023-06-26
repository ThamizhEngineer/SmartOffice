import { FileModule } from './../../../shared/file.module';
import { CommonService } from '../../../shared/common/common.service';
import { Response, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import { CashBalanceService } from './cash-balance.service';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { CashBalanceComponent } from './comp/cash-balance-list.component';
import {CashBalanceDetailComponent} from './comp/cash-balance-detail.component';
import { CashBalanceRoutes } from './cash-balance.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from './../../../shared/shared.module';
import { Headers,Http, RequestOptions, URLSearchParams} from '@angular/http';



@NgModule({
imports: [
CommonModule, NgbModule, FormsModule,ReactiveFormsModule,SharedModule,FileModule,
RouterModule.forChild(CashBalanceRoutes)
],
declarations: [CashBalanceComponent,CashBalanceDetailComponent],
providers:[CashBalanceService,CommonService]
})

export class CashBalanceModule { }


