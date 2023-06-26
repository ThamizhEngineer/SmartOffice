import { Routes } from '@angular/router';

import { ClientProfileComponent } from './_comp/client-profile.component';
import { ClientProfileEditComponent } from './_comp/client-profile-edit.component';
import { ClientListComponent } from './_comp/client-list.component';
import { ClientProfileAddComponent } from './_comp/client-profile-add.component';
import { InvoicePaymentModule } from '../transaction/invoice-payment/invoice-payment.module';
import { InvoiceModule } from '../transaction/invoice/invoice.module';
import { MySpaceClientComponent } from './_comp/my-space-client.component';

export const ClientRoutes: Routes = [
    
    { path: 'view/:_id', component: ClientProfileComponent },
    { path: 'new', component: ClientProfileAddComponent },
    { path: 'edit/:_id', component: ClientProfileEditComponent },
    { path: 'edit/', component: ClientProfileEditComponent },
    { path: 'list', component: ClientListComponent },
    { path: 'job-feedback',loadChildren:'../job/job-feedback/job-feedback.module#JobFeedbackModule'},
    { path: 'item-master',loadChildren:'../transaction/item-master/item-master.module#ItemMasterModule'},
    { path: 'invoice-payment',loadChildren:'../transaction/invoice-payment/invoice-payment.module#InvoicePaymentModule'},
    { path: 'invoice',loadChildren:'../transaction/invoice/invoice.module#InvoiceModule'},
    { path: 'my-space-client',component:MySpaceClientComponent},
    { path: '**',redirectTo: 'list' }
];
