import { Routes } from '@angular/router';
import { PayslipsListComponent } from './_comp/list.component';
import { PayslipsDetailComponent } from './_comp/detail.component';
import { PayslipsNewComponent } from './_comp/new.component';

export const PayslipsRoutes: Routes = [
	{ path: '', component: PayslipsListComponent },
	{ path: 'detail/:_id', component: PayslipsDetailComponent },
	{ path: 'overwrite/:hid/:hlid', component: PayslipsNewComponent },
	{ path: 'new', component: PayslipsNewComponent },
    { path: '**', redirectTo: '' },
];
