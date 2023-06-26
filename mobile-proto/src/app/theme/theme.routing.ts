import { Routes } from "@angular/router";
import { ThemeComponent } from "./theme.component";
import { DashboardPageModule } from "../pages/dashboard/dashboard.module";
import { ChatPageModule } from "../pages/chat/chat.module";
import { MyProfilePageModule } from "../pages/my-profile/my-profile.module";
import { TarModule } from "../pages/tar/tar.module";
import { ExpenseClaimModule } from "../pages/expense-claim/expense-claim.module";
import { AuthGuard } from "../auth/_guards/auth.guard";
import {LeavePageModule} from "../pages/leave/leave.module"
import { NotifPageModule } from "../pages/notification/notif/notif.module";
import { AttendancePageModule } from "../pages/attendance/attendance.module";

export const ThemeRoutes: Routes =[{
    path: '', component: ThemeComponent, 'canActivate': [AuthGuard], 'children':[
        { path:'dashboard'      , loadChildren:()=> DashboardPageModule},
        { path: 'chat', loadChildren: ()=>ChatPageModule},
        { path: 'account', loadChildren: ()=>MyProfilePageModule},
        { path: 'tar', loadChildren: ()=>TarModule},
        { path: 'expense-claim', loadChildren: ()=>ExpenseClaimModule},
        { path: 'leave', loadChildren: ()=>LeavePageModule},
        { path: 'notif', loadChildren: ()=>NotifPageModule},
        { path: 'attendance', loadChildren: ()=>AttendancePageModule},
        { path: ''              , redirectTo: 'dashboard', pathMatch: 'full'},
      
    ]
}];