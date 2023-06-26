import { Routes } from '@angular/router';
import { LeaveApprovelDetailComponent } from './comp/leave-approvel-detail.component';
import { LeaveApprovalRequestComponent } from './comp/leave-approvel-view.component';
import { Component } from '@angular/core';

export const LeaveApprovalRoutes: Routes = [{
    path: '',
    children: [
        { path: 'view', component: LeaveApprovalRequestComponent},
        { path: 'detail/:_id', component: LeaveApprovelDetailComponent},
        { path: '**', redirectTo: 'view' }
    ]
}];
