import { Routes, RouterModule } from '@angular/router';
import { MyTarAcc2ApprovceComponent } from './comp/my-tar-acc2-approve-list.component';
import { Acc2ApproveComponent } from './comp/my-tar-acc2.component';

export const MyTarAcc2ApproveRoutes: Routes = [
    { path: 'approve', component: Acc2ApproveComponent },
    { path: 'list', component: MyTarAcc2ApprovceComponent },
    { path: '**', redirectTo: 'list' }

]

