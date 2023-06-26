import { Routes } from '@angular/router';

export const RecurimentRoutes: Routes = [
  { path: 'job-description', loadChildren: '../master/vacancy-description/vacancy-description.module#VacancyDescriptionModule' },
  { path: 'job-request', loadChildren: '../transaction/vacancy-request/vacancy-request.module#VacancyRequestModule' },
  { path: 'job-opening', loadChildren: '../transaction/vacancy-opening/vacancy-opening.module#VacancyOpeningModule' },
  { path: 'web-applicants', loadChildren: '../transaction/web-site-applications/web-applications.module#WebApplicationModule' },
  { path: 'event', loadChildren: '../transaction/incident/incident.module#IncidentModule' },
  { path:'interview',loadChildren:'../transaction/interview-tracker/interview-tracker.module#InterviewTrackerModule'},
  { path:'test-category',loadChildren:'../master/test-category/test-category.module#TestCategoryModule'},
  { path:'test-template',loadChildren:'../knowledge-assessment/test-template/test-template.module#TestTemplateModule'},
  { path:'certificate-tracker',loadChildren:'../recruitment/certificate.module#CertificateModule'},
  { path:'recruitment-master',loadChildren:'../master/recruitment-master/recruitment.module#RecruitmentModule'},
  { path:'applicant',loadChildren:'../operation/applicant/applicant.module#ApplicantModule'},
  { path: '**', redirectTo: 'job-description' },
];
