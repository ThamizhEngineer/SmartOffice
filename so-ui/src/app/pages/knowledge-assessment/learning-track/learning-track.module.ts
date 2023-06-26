import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{LearningTrackRoutes} from './learning-track.routing';
import{LearningTrackListComponent} from './learning-track-list.component';
import{LearningTrackDetailComponent} from './learning-track-detail.component';
import { FileModule } from './../../../shared/file.module';
import{LearningTrackService} from '../learning-track/learning-track.service';
@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,FileModule,
        RouterModule.forChild(LearningTrackRoutes),
    ],
    providers: [LearningTrackService],
    declarations: [LearningTrackListComponent,LearningTrackDetailComponent]
})


export class LearningTrackModule { }
