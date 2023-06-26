import { Routes } from '@angular/router';

import { CustomerProfileComponent } from './_comp/customer-profile.component';
import { CustomerProfileEditComponent } from './_comp/customer-profile-edit.component';
import { CustomerListComponent } from './_comp/customer-list.component';

export const CustomerRoutes: Routes = [
    
    { path: 'customer-profile', component: CustomerProfileComponent },
    { path: 'customer-profile-edit', component: CustomerProfileEditComponent },
    { path: 'customer-list', component: CustomerListComponent },
      { path: '**',redirectTo: 'customer-list' }
 
];
