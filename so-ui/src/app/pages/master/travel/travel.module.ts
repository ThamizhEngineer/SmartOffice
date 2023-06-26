import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import {TravelApprovalComponent} from '../travel/_comp/travel-approval.component'
import { TravelDetailComponent} from '../travel/_comp/travel-detail.component'
import { TravelRequestComponent} from '../travel/_comp/travel-request.component'


import { TravelRoutes } from './travel.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(TravelRoutes),
    ],
    declarations: [TravelApprovalComponent,TravelDetailComponent,TravelRequestComponent]
    
})

export class TravelModule { }
