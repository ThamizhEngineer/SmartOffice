import { Routes } from '@angular/router';

export const KnowledgeAssessmentRoutes: Routes = [

 
    {path:'add',loadChildren:'./questions/add.module#AddModule'},
    
    {path:'add-test-participants',loadChildren:'./add-test-participants/add-test-participants.module#AddTestParticipantsModule'},
    {path:'create-test',loadChildren:'./create-test/create-test.module#CreateTestModule'},
    {path:'test-result',loadChildren:'./test-result/result.module#TestResultModule'},
    { path: '**', redirectTo: 'add-list' }
];