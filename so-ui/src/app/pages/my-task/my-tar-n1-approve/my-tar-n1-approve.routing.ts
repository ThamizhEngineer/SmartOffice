import { Routes, RouterModule } from '@angular/router';
import { MyTarN1ApproveComponent } from './comp/my-tar-n1-approve-list.component';
import { N1ApproveComponent } from './comp/my-tar-n1-approve.component';

export const MyTarN1ApproveRoutes: Routes = [

    { path: 'approve', component: N1ApproveComponent },
    { path: 'list', component: MyTarN1ApproveComponent },
    { path: '**', redirectTo: 'list' }

]

