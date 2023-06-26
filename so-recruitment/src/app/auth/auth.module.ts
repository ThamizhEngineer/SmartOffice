import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BaseRequestOptions, HttpModule } from "@angular/http";
import { MockBackend } from "@angular/http/testing";

import { RouterModule, Routes } from "@angular/router";

import { AuthComponent } from "./_comp/auth.component";
import { AlertComponent } from "./_comp/alert.component";
import { LogoutComponent } from "./_comp/logout.component";
import { AuthGuard } from "./_guards/auth.guard";
import { AlertService } from "./_services/alert.service";
import { AuthenticationService } from "./_services/authentication.service";
import { UserService } from "./_services/user.service";
import { ApplicantService } from '../pages/my-space/applicant/applicant.service';
import { CommonService } from '../shared/common/common.service';

const routes: Routes = [
    { path: '', component: AuthComponent }
];

@NgModule({
    declarations: [
        AuthComponent,
        AlertComponent,
        LogoutComponent,
    ],
    imports: [
        CommonModule,
        FormsModule,
        HttpModule,
        RouterModule.forChild(routes),ReactiveFormsModule,
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
        ApplicantService,
        CommonService,
    ],
    entryComponents: [AlertComponent]
})

export class AuthModule {
}