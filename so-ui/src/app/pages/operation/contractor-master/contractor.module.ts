import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { JobService} from '../../job/job.service';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FileModule } from './../../../shared/file.module';
import { ContractorDetailComponent } from './_comp/contractor-detail.component';
import { ContractorListComponent } from './_comp/contractor-list.component';
import { ContractorService } from '../contractor-master/contractor.service';
import{ContractorRoutes} from './contractor.routing';
import { DateParserService } from '../../../shared/_services/date-parser.service';
import { CommonService } from '../../../shared/common/common.service';
@NgModule({
  imports: [
    CommonModule, FormsModule,ReactiveFormsModule,SharedModule, NgbModule,FileModule,
    RouterModule.forChild(ContractorRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBn0At6iILxOg4RYquknGpqGxkaeETfflg'
    })
  ], 

  declarations: [ 
    ContractorListComponent,ContractorDetailComponent
  ],
  providers:[ContractorService,DateParserService,JobService,CommonService]
})
export class ContractorModule { }
