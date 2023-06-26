import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonService } from '../../../shared/common/common.service';
import { FileModule } from '../../../shared/file.module';
import { ExitInterviewComponent } from '../exit-interview/_comp/exit-interview.component';
import { ExitInterviewVoluntaryComponent } from '../exit-interview/_comp/exit-interview-voluntary.component';
import { ExitInterviewInVoluntaryComponent } from '../exit-interview/_comp/exit-interview-involuntary.component';
import { ExitInterviewHrComponent } from '../exit-interview/_comp/exit-interview-hr.component';
import { ExitInterviewRoutes } from './exit-interview.routing';
import{ExitInterviewService} from '../exit-interview/exit-interview.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from './../../../shared/shared.module';
import { ExitInterviewApproveComponent } from './_comp/exit-interview-approve.component'

@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,SharedModule,
        RouterModule.forChild(ExitInterviewRoutes),NgbTooltipModule
    ],
    providers: [ ExitInterviewService, CommonService],
    declarations: [ExitInterviewComponent,ExitInterviewApproveComponent,ExitInterviewVoluntaryComponent,ExitInterviewInVoluntaryComponent, ExitInterviewHrComponent]
})

export class ExitInterviewModule { }