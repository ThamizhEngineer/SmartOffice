import { NgModule } from '@angular/core';

import { ExpenseCategoryComponent } from './comp/expense-category.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { ExpenseCategoryService } from './expense-category.service';
import { ExpenseCategoryRoutes } from './expense-category.routing';

@NgModule({
    imports: [ CommonModule,FormsModule,NgMultiSelectDropDownModule,FileModule,NgbModule,RouterModule.forChild(ExpenseCategoryRoutes),SharedModule ],
    exports: [],
    declarations: [ExpenseCategoryComponent],
    providers: [ExpenseCategoryService],
})
export class ExpenseCategoryModule { }
