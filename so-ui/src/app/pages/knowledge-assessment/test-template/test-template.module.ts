import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormWizardModule } from 'angular2-wizard';
import { SharedModule } from './../../../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import{TestTemplateRoutes} from './test-template.routing';
import{TestTemplateService} from '../test-template/test-template.service';
import{TestTemplateListComponent} from './_comp/test-template-list.component';
import{TestTemplateDetailComponent} from './_comp/test-template-detail.component';

@NgModule({
  imports: [
    CommonModule, FormsModule, NgbModule,FormWizardModule,SharedModule,
    RouterModule.forChild(TestTemplateRoutes),ReactiveFormsModule
  ],
  providers:[TestTemplateService],
  declarations: [ TestTemplateListComponent,TestTemplateDetailComponent,
    
  ]
})
export class TestTemplateModule { }