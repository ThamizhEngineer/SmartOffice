import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { LogoutComponent } from './pages/login/logout.component';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./index/index.module').then(m => m.IndexPageModule)
  },
  {
    path: '',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  { 
    path: 'logout',
    component: LogoutComponent 
  },
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
