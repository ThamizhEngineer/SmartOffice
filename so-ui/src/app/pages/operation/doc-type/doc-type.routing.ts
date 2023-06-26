import { Routes } from '@angular/router';

import { DocTypeComponent } from '../doc-type/comp/doc-type.component';

export const DocTypeRoutes: Routes = [
    { path: 'doc', component: DocTypeComponent},
    { path: '**', redirectTo: 'doc' }
];