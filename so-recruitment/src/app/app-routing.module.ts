import { Routes } from '@angular/router';
import { LogoutComponent } from "./auth/_comp/logout.component";

export const AppRoutes: Routes = [
    { path: '', loadChildren: './theme/theme.module#ThemeModule' },
    { path: 'login', loadChildren: './auth/auth.module#AuthModule' },
    { path: 'logout', component: LogoutComponent },
    { path: '**', redirectTo: 'index', pathMatch: 'full' },
];
