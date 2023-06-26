import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CommonService } from '../../shared/common/common.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FullCalendarModule } from 'ng-fullcalendar';
import { SharedModule } from './../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthorisationRoutes } from './authorisation.routing';
import { AuthorisationComponent } from './authorisation/comp/authorisation.component';
import { FunctionalityComponent } from './functionality/comp/functionality.component';
import { FeatureComponent } from './feature/comp/feature.component';
import { AuthorisationService } from './authorisation.service';
import { RoleComponent } from './role/comp/role.component';
import { RoleFeatureComponent } from './role-feature-mapping/comp/role-feature.component';
import { UserTypeComponent } from './user-type/comp/user-type.component';
import { UserComponent } from './user/comp/user.component';
import { MenuComponent } from './menu/comp/menu.component';
import { SubMenuComponent } from './sub-menu/comp/sub-menu.component';
import { UserViewComponent } from './user-view/comp/user-view.component';

@NgModule({
    imports: [
        CommonModule,NgbModule,SharedModule,FullCalendarModule,FormsModule,ReactiveFormsModule,
        RouterModule.forChild(AuthorisationRoutes)
    ],
    exports: [],
    declarations: [AuthorisationComponent,FunctionalityComponent,FeatureComponent,RoleComponent,RoleFeatureComponent,UserTypeComponent,UserComponent,MenuComponent,SubMenuComponent,UserViewComponent],
    providers: [CommonService,AuthorisationService],
})
export class AuthorisationModule { }
