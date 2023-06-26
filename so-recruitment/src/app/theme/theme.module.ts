import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ThemeComponent } from './theme.component';
import { ThemeRoutes } from './theme.routing';
import { DefaultModule } from './default/default.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { SearchService } from './../shared/_services/search.service';


@NgModule({
    imports: [
        CommonModule, NgbModule,
        RouterModule.forChild(ThemeRoutes),
		PerfectScrollbarModule,
        DefaultModule
    ],
	providers:[SearchService],
    declarations: [ThemeComponent]
})

export class ThemeModule { }
