import { Routes } from '@angular/router';
import { AuthorisationComponent } from './authorisation/comp/authorisation.component';
import { FunctionalityComponent } from './functionality/comp/functionality.component';
import { FeatureComponent } from './feature/comp/feature.component';
import { RoleComponent } from './role/comp/role.component';
import { RoleFeatureComponent } from './role-feature-mapping/comp/role-feature.component';
import { UserTypeComponent } from './user-type/comp/user-type.component';
import { UserComponent } from './user/comp/user.component';
import { MenuComponent } from './menu/comp/menu.component';
import { SubMenuComponent } from './sub-menu/comp/sub-menu.component';
import { UserViewComponent } from './user-view/comp/user-view.component';
// import { AuthorisationModule } from './authorisation.module'
// import { MenuComponent } from './menu/comp/menu.component';

// import { PurchaseOrderModule } from '../job/purchase-order/purchase-order.module';

export const AuthorisationRoutes: Routes = [
    { path: 'child', loadChildren:'./authorisation.module#AuthorisationModule'},

    { path: 'setup', component: AuthorisationComponent },
    { path: 'functionality', component: FunctionalityComponent },
    { path: 'feature', component: FeatureComponent },
    { path: 'role', component: RoleComponent },
    { path: 'RoleFeature', component: RoleFeatureComponent },
    { path: 'UserType', component: UserTypeComponent },
    { path: 'User', component: UserComponent },
    { path: 'menu', component: MenuComponent },
    { path: 'sub-menu', component: SubMenuComponent },
    { path: 'user-view', component: UserViewComponent }
    // { path: 'purchase-order',loadChildren:'../job/purchase-order/purchase-order.module#PurchaseOrderModule'},
    // { path: '**',redirectTo: 'setup' }
];





