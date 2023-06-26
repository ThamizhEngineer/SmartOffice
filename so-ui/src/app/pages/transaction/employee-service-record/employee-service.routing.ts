import { Routes } from '@angular/router';
import { EmployeeServiceComponent} from './comp/employee-service.component';


export const EmployeeServiceRoutes: Routes = [{
    path: '',
    children: [
        { path: 'EmpSer', component: EmployeeServiceComponent},
        { path: '**', redirectTo: 'EmpSer' }
    ]
}];