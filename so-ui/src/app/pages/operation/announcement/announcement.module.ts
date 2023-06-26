import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

import { AnnouncementComponent } from './comp/announcement.component';
import { AnnouncementService } from './announcement.service';
import { AnnouncementRoutes } from './announcement.routing';

@NgModule({
    imports: [ CommonModule,FormsModule,NgMultiSelectDropDownModule,FileModule,NgbModule,RouterModule.forChild(AnnouncementRoutes),SharedModule ],
    exports: [],
    declarations: [AnnouncementComponent],
    providers: [ AnnouncementService ],
})
export class AnnouncementModule { }
