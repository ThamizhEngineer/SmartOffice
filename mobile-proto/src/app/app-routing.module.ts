import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogoutComponent } from './auth/_comp/logout.component';
import { ThemeModule } from './theme/theme.module';
import { AuthModule } from './auth/auth.module';

const routes: Routes = [
  { path: '',       loadChildren: ()=> ThemeModule },
  { path: 'auth',  loadChildren: ()=> AuthModule },
  { path: 'logout', component: LogoutComponent },
  { path: '**',     redirectTo: 'dashboard', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
