import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ManagerSwapService } from './manager-swap.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{ManagerSwapRoutes} from './manager-swap.routing';
import{ManagerSwapDetailComponent} from './manager-swap-detail.component';
import { SharedModule} from '../../../shared/shared.module'

@NgModule({
    imports: [
        CommonModule,SharedModule, NgbModule,FormsModule,
        RouterModule.forChild(ManagerSwapRoutes),
    ],
    providers: [ManagerSwapService],
    declarations: [ManagerSwapDetailComponent]
})


export class ManagerSwapModule { }

