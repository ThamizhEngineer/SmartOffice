import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';

import {
    FormGroup,
    FormBuilder,
    Validators,
    FormControl
  } from '@angular/forms';
import { SortablejsModule } from 'angular-sortablejs';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

import { AppRoutes } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from "./auth/auth.module";
import { NgxDocViewerModule } from 'ngx-doc-viewer';

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        NgxDocViewerModule,
        BrowserModule,
        NgbModule.forRoot(),
        BrowserAnimationsModule,
		    PerfectScrollbarModule,
        AuthModule,
		SortablejsModule.forRoot({ animation: 200 }),
		RouterModule.forRoot(AppRoutes)
    ],
    bootstrap: [AppComponent],
	providers: [
		{
		  provide: PERFECT_SCROLLBAR_CONFIG,
		  useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
		}
	  ]
})
export class AppModule { }