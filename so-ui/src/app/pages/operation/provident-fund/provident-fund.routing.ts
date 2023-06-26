import { Routes } from '@angular/router';

import { ProvidentFundComponent } from './_comp/provident-fund.component';

import { AddProvidentFundComponent } from './_comp/add-provident-fund.component';
export const ProvidentFundRoutes: Routes = [{
    path: '',
    children: [
        { path: 'provident-fund', component: ProvidentFundComponent,  pathMatch: 'full' },
        { path: 'add-provident-fund', component: AddProvidentFundComponent },
        { path: '**', redirectTo: 'provident-fund' }
    ]
}];
