import { Routes, RouterModule } from '@angular/router';
import { MyTarRequestListComponent } from './comp/my-tar-request-list.component';
import { MyTarRequestComponent } from './comp/my-tar-request.component';

export const MyTarRequestRoutes: Routes = [


    { path: 'create', component: MyTarRequestComponent },
    { path: 'edit', component: MyTarRequestComponent },
    { path: 'list', component: MyTarRequestListComponent },
    { path: '**', redirectTo: 'list' }

]

