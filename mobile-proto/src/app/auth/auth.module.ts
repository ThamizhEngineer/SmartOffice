import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { IonicModule } from '@ionic/angular';
import { FormsModule } from '@angular/forms';

import { AuthenticationService } from '../services/authentication.service';
import { AuthGuard } from './_guards/auth.guard';

import { LoginPage } from './_comp/login.page';
import { SignUpPage } from './sign-up/sign-up.page';
import { ForgotPasswordPage } from './forgot-password/forgot-password.page';

const AuthRoutes: Routes=[
    { path: 'login', component: LoginPage },
    { path: 'signup', component: SignUpPage },
    { path: 'forgot', component: ForgotPasswordPage },
    { path: '**',     redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  declarations: [ LoginPage,SignUpPage,ForgotPasswordPage ],
  imports: [
    CommonModule, IonicModule, FormsModule,
    RouterModule.forChild(AuthRoutes)
  ],
  providers:[
    AuthenticationService,
    AuthGuard
  ]
})
export class AuthModule { }
