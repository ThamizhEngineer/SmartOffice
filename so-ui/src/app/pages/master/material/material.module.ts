import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { MaterialComponent } from './comp/material.component';
import { SharedModule } from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MaterialsService } from './material.service';



const routes: Routes = [
    {path:"",component:MaterialComponent}
]

@NgModule({
    imports: [CommonModule,FormsModule,ReactiveFormsModule,NgbModule,SharedModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [ MaterialComponent ],
    providers: [ MaterialsService ]
})
export class MaterialModule { }