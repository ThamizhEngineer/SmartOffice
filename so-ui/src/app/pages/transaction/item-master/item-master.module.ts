import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module'
import { FileModule } from './../../../shared/file.module';
import { ItemMasterComponent } from './comp/item-master.component';
import { ItemMasterService } from './item-master.service';
import { ItemMasterDetailComponent } from './comp/item-master-detail.component';
// import { ItemMasterRoutes } from './item-master.routing';

const routes: Routes = [
    {path:'',component:ItemMasterComponent},
    {path:'view/:id',component:ItemMasterDetailComponent},
    {path:'new',component:ItemMasterDetailComponent}
]

@NgModule({
    imports: [ CommonModule, NgbModule, FormsModule,SharedModule,ReactiveFormsModule,FileModule,
        RouterModule.forChild(routes)],
    exports: [],
    declarations: [ItemMasterComponent,ItemMasterDetailComponent],
    providers: [ItemMasterService],
})
export class ItemMasterModule { }
