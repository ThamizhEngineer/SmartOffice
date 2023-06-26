import { Routes } from '@angular/router';


import { MyProfileComponent } from './_comp/my-profile.component';
export const MyProfileRoutes: Routes = [
  
    { path: 'my-profile', component: MyProfileComponent },
    { path: '**',redirectTo: 'my-profile' }
 
];
