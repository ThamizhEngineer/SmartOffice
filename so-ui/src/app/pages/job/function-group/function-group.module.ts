import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { FunctionGroupListComponent } from './comp/function-group-list.component';
import { FunctionGroupDetailComponent } from './comp/function-group-detail.component';
import { FunctionGroupRoutes } from './function-group.routing';
import { FunctionGroupService } from './function-group.service';

@NgModule({
    imports: [CommonModule, NgbModule, FormsModule,ReactiveFormsModule,SharedModule,FileModule,
        RouterModule.forChild(FunctionGroupRoutes)],
    exports: [],
    declarations: [FunctionGroupListComponent, FunctionGroupDetailComponent],
    providers: [FunctionGroupService],
})
export class FunctionGroupModule { }
