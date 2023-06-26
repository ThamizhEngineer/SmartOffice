import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ThemeComponent } from './theme.component';
import { ThemeRoutes } from './theme.routing';
import { DefaultModule } from './default/default.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { SearchService } from './../shared/_services/search.service';
import { InAppNotfnService } from './default/header-nav/in-app-notfn.service';
import { CommonService } from '../shared/common/common.service';


@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(ThemeRoutes),
		PerfectScrollbarModule,
        DefaultModule
    ],
	providers:[SearchService,InAppNotfnService,CommonService],
    declarations: [ThemeComponent]
})

export class ThemeModule { }
