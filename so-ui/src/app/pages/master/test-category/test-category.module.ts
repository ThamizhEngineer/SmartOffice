import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module';
import { NgxPaginationModule } from 'ngx-pagination';
import { FileModule } from '../../../shared/file.module';


import { TestCategoryComponent } from './comp/test-category.component';
import { QuestionComponent } from './comp/questions.component';
import { TestCategoryService } from './test-category.service';

const routes: Routes = [
    {path:'',component:TestCategoryComponent},
    {path:'questions/:id',component:QuestionComponent}
]

@NgModule({
    imports: [CommonModule,NgxPaginationModule,FormsModule,FileModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [TestCategoryComponent,QuestionComponent],
    providers:[TestCategoryService] 
})
export class TestCategoryModule { }