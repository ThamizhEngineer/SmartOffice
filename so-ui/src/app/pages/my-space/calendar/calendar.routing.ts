import { Routes } from '@angular/router';

import { CalendarFullComponent } from './_comp/full.component';


export const CalendarRoutes: Routes = [
    { path: '', component: CalendarFullComponent },
    { path: '**', redirectTo: '' },
];
