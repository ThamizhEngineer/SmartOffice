import { Routes } from '@angular/router';

import { SettingsDetailComponent } from './_comp/detail.component';


export const SettingsRoutes: Routes = [
    { path: '', component: SettingsDetailComponent },
    { path: '**', redirectTo: '' },
];
