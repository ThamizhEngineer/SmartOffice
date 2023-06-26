import { Routes } from '@angular/router';

import { CtcDefinitionComponent } from './definition/_comp/definition.component';
import { CompensationShowComponent } from './compensation/_comp/show.component';
import { GradeCompensationShowComponent } from './grade/_comp/show.component';

export const CtcSettingsRoutes: Routes = [
    { path: 'definition', component: CtcDefinitionComponent },
    { path: 'definition/:fyear', component: CtcDefinitionComponent },
    { path: 'compensation', component: CompensationShowComponent },
    { path: 'compensation/:_id', component: CompensationShowComponent },
	{ path: 'grade-compensation', component: GradeCompensationShowComponent },
    { path: 'grade-compensation/:_id', component: GradeCompensationShowComponent },
    { path: '**', redirectTo: 'definition' },
];
