import { Routes } from '@angular/router';
export const KnowledgeAssessmentRoutes: Routes = [

    {path:'test-template',loadChildren:'./test-template/test-template.module#TestTemplateModule'},
 
    {path:'add',loadChildren:'./questions/add.module#AddModule'},
    {path:'learning-track',loadChildren:'./learning-track/learning-track.module#LearningTrackModule'},
    {path:'test',loadChildren:'./questions/test.module#TestModule'},
    { path: '**', redirectTo: 'add-list' }
];