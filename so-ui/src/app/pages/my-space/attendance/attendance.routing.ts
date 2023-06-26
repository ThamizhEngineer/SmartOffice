import { Routes } from '@angular/router';

import { AttendanceShowComponent } from './_comp/show.component';
import { AttendanceProxyComponent } from './_comp/proxy.component';
//import { AttendanceDirectShowComponent } from './_comp/direct.component';

export const AttendanceRoutes: Routes = [
    
    { path: '', component: AttendanceShowComponent },
    { path: 'show', component: AttendanceShowComponent },
    { path: 'proxy', component: AttendanceProxyComponent },
//    { path: 'direct', component: AttendanceDirectShowComponent },
//    { path: 'direct/:id', component: AttendanceDirectShowComponent },

	{ path: '**',redirectTo: '' }
 
];
