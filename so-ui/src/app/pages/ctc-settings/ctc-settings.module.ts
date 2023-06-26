import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SharedModule } from './../../shared/shared.module';

import { CtcDefinitionComponent } from './definition/_comp/definition.component';
import { CompensationShowComponent } from './compensation/_comp/show.component';
import { GradeCompensationShowComponent } from './grade/_comp/show.component';
import { CompensationService } from './compensation.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { CtcSettingsRoutes } from './ctc-settings.routing';

@NgModule({
	imports: [
		CommonModule, NgbModule, FormsModule, SharedModule,
		RouterModule.forChild(CtcSettingsRoutes),
	],
	providers: [ CompensationService ],
	declarations: [ CtcDefinitionComponent, CompensationShowComponent, GradeCompensationShowComponent ]
})
export class CtcSettingsModule { }
