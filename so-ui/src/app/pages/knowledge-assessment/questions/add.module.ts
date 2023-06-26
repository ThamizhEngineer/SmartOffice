import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }   from '@angular/forms';
import{AddRoutes} from './add.routing';
import{QuestionsAddDetailComponent} from './_comp/add-detail.component';
import{QuestionsAddListComponent} from './_comp/add-list.component';
@NgModule({
    imports: [
        CommonModule, NgbModule,FormsModule,
        RouterModule.forChild(AddRoutes),
    ],
   
    declarations: [QuestionsAddDetailComponent,QuestionsAddListComponent]
})


export class AddModule { }
