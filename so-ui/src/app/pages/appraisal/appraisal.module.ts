import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppraisalRoutes } from './appraisal.routing';
import { MetricListComponent } from './metrics/list.component';
import { SharedModule } from '../../shared/shared.module';
import { AppraisalService } from './appraisal.service';

@NgModule({
    imports: [
      CommonModule, FormsModule,ReactiveFormsModule, NgbModule,SharedModule,
      RouterModule.forChild(AppraisalRoutes),
      
    ],
    providers:[AppraisalService],
    declarations: [ 
      MetricListComponent
    ]
  })
  export class AppraisalModule { }
  