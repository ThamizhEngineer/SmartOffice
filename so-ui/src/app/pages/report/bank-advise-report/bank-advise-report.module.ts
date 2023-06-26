import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import { BankAdviseReportRoutes } from './bank-advise-report.routing';
import { BankAdviseReportListComponent } from './_comp/bank-advise-list.component';

import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,
        RouterModule.forChild(BankAdviseReportRoutes),
    ],
    providers: [],
    declarations: [BankAdviseReportListComponent]
})


export class BankAdviseReportModule { }
