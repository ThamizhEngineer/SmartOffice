import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { HeaderNavComponent } from './header-nav/header-nav.component';
import { AsideNavComponent } from './aside-nav/aside-nav.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule } from '@angular/forms';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
    imports: [
        CommonModule, NgbModule.forRoot(),RouterModule, PerfectScrollbarModule, FormsModule, SharedModule
    ],
    declarations: [HeaderNavComponent, AsideNavComponent, FooterComponent ],
    exports: [HeaderNavComponent, AsideNavComponent, FooterComponent ],
})

export class DefaultModule { }
