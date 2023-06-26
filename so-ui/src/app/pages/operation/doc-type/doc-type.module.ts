import { NgModule } from '@angular/core';

import { DocTypeComponent } from '../doc-type/comp/doc-type.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { DocTypeService } from './doc-type.service';
import { DocTypeRoutes } from './doc-type.routing';

@NgModule({
    imports: [ CommonModule,FormsModule,NgMultiSelectDropDownModule,FileModule,NgbModule,RouterModule.forChild(DocTypeRoutes),SharedModule ],
    exports: [],
    declarations: [DocTypeComponent],
    providers: [DocTypeService],
})
export class DocTypeModule { }
