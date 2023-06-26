import { Routes } from '@angular/router';
import{TestTemplateListComponent} from './_comp/test-template-list.component';
import{TestTemplateDetailComponent} from './_comp/test-template-detail.component';
export const TestTemplateRoutes: Routes = [
    { path: 'test-template-detail', component:TestTemplateDetailComponent },
    { path: 'test-template-detail/:_id', component:TestTemplateDetailComponent },
    { path: 'test-template-list', component:TestTemplateListComponent  },
    { path: '**', redirectTo: 'test-template-list' }
];