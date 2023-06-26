import { Routes } from '@angular/router';
import { ExitInterviewComponent } from '../exit-interview/_comp/exit-interview.component';
import { ExitInterviewVoluntaryComponent } from '../exit-interview/_comp/exit-interview-voluntary.component';
import { ExitInterviewInVoluntaryComponent } from '../exit-interview/_comp/exit-interview-involuntary.component';
import { ExitInterviewHrComponent } from '../exit-interview/_comp/exit-interview-hr.component';
import { ExitInterviewApproveComponent } from './_comp/exit-interview-approve.component';
export const ExitInterviewRoutes: Routes = [
  
   
        { path: '', component: ExitInterviewComponent },
        { path: 'exit-interview-voluntary', component: ExitInterviewVoluntaryComponent},
        { path: 'exit-interview-voluntary/:_id', component: ExitInterviewVoluntaryComponent},
        { path: 'exit-interview-involuntary', component: ExitInterviewInVoluntaryComponent},
        { path: 'exit-interview-involuntary/:_id', component: ExitInterviewInVoluntaryComponent},
        { path: 'exit-interview-hr', component: ExitInterviewHrComponent},
        { path: 'exit-interview-hr/:_id', component: ExitInterviewHrComponent},
        { path: ':view', component: ExitInterviewApproveComponent},
        { path: '**', redirectTo: '' }
    
];
