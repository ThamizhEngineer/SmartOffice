import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MyProfileComponent } from './_comp/my-profile.component';


import { MyProfileRoutes } from './my-profile.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
        CommonModule, NgbModule, FormsModule,
        RouterModule.forChild(MyProfileRoutes),
    ],
    declarations: [MyProfileComponent]
})

export class MyProfileModule { }
