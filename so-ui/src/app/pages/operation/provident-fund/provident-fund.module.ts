import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ProvidentFundComponent } from './_comp/provident-fund.component';

import { AddProvidentFundComponent } from '../provident-fund/_comp/add-provident-fund.component'
import { ProvidentFundRoutes } from './provident-fund.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(ProvidentFundRoutes),
    ],
    declarations: [AddProvidentFundComponent,ProvidentFundComponent]
})

export class ProvidentFundModule { }
