import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FileModule } from '../../../shared/file.module';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';

import { CKEditorModule } from '@ckeditor/ckeditor5-angular';


// import { TagInputModule } from 'ngx-chips';
import { FAQCategoryComponent } from './comp/faq-category.component';
import { FaqComponent } from './comp/faq-test.component';
import { FAQListComponent } from './comp/faq-list.component';
import { FAQDetailComponent } from './comp/faq-detail.component';
import { FAQService } from './faq.service';

const routes: Routes = [
    {path:'',component:FAQCategoryComponent},  
    {path:'test',component:FaqComponent},
    {path:':id',component:FAQListComponent},
    {path:'faq-detail/:view/:id',component:FAQDetailComponent},  
    {path:'create-post/:view/:faqCatId',component:FAQDetailComponent},
]

@NgModule({
    imports: [PerfectScrollbarModule,CKEditorModule,CommonModule,NgbModule,FormsModule,FileModule,SharedModule,ReactiveFormsModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [FaqComponent,FAQCategoryComponent,FAQDetailComponent,FAQListComponent],
    providers: [FAQService],
})
export class FaqModule { }
