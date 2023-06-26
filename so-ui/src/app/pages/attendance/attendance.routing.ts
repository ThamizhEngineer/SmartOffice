import { Routes } from '@angular/router';

export const AttendanceRoutes: Routes = [    
     { path: 'attendance-shift', loadChildren:'../master/attendance-shift/attendance-shift.module#AttendanceShiftModule' },    
    { path: '**', redirectTo: 'attendance-shift' },
];