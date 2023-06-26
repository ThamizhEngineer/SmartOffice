import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './comp/index.component';

export const IndexnRoutes: Routes = [

    { path: '', component: IndexComponent },
    { path: '**', redirectTo: '' }

]