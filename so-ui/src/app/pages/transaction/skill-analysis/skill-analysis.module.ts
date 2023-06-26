import { NgModule } from '@angular/core';
import {Routes,RouterModule  } from '@angular/router'
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule} from '../../../shared/shared.module';
import { FileModule } from '../../../shared/file.module';
import { GapAnalysisComponent } from './comp/gap-analysis.componet';
import { SkillAnalysisService } from './skill-analysis.service';
import { SkillAnalysisListComponent } from './comp/skill-analysis-list.componet'
import { SkillProcessComponent } from './comp/skill-process.componet'
import { EngAssesmentComponent } from './comp/eng-assesment.component';
const routes : Routes =[    
    {path:'',component:SkillAnalysisListComponent},
    {path:'process/:type',component:SkillProcessComponent},
    {path:'gap-analysis/:key',component:GapAnalysisComponent},
    {path:'eng-assesment/:key',component:EngAssesmentComponent}
]


@NgModule({
    imports: [CommonModule,FormsModule,SharedModule,NgbModule,FileModule,RouterModule.forChild(routes)],
    exports: [],
    declarations: [GapAnalysisComponent,SkillAnalysisListComponent,SkillProcessComponent,EngAssesmentComponent],
    providers: [SkillAnalysisService],
})
export class SkillAnalysisModule { }
