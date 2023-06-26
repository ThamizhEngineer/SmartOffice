import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MyTaskRoutes } from './my-task.routing';
import { CommonService } from '../../shared/common/common.service';
import { MyTarRequestService } from './my-tar.service';
import { SharedModule } from './../../shared/shared.module';
import { TypeaheadPopupDirective } from './popup.directive';

@NgModule({
  imports: [
    CommonModule,SharedModule,RouterModule.forChild(MyTaskRoutes)
  ],
  providers: [  CommonService,MyTarRequestService ],
  declarations: [ TypeaheadPopupDirective ]
})
export class MyTaskModule { }
