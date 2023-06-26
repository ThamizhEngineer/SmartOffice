import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '../../../shared/shared.module';
import { NotificationRuleRoutes } from './notification-rule.routing';
import { NotificationRuleListComponent } from './comp/notification-rule-list.component';
import { NotificationRuleService } from './notification-rule.service';

@NgModule({
    imports: [ CommonModule,FormsModule,NgbModule,RouterModule.forChild(NotificationRuleRoutes),SharedModule],
    exports: [],
    declarations: [NotificationRuleListComponent],
    providers: [NotificationRuleService],
})
export class NotificationRuleModule { }