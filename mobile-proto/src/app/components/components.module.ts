import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommonHeaderComponent } from './common-header/common-header.component';
import { CommonModalComponent } from './common-modal/common-modal.component';
import { CommonPopoverComponent } from './common-popover/common-popover.component';
import { SubpageHeaderComponent } from './subpage-header/subpage-header.component';
import { ChildPopoverComponent } from './child-popover.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    CommonHeaderComponent,
    CommonModalComponent,
    CommonPopoverComponent,
    SubpageHeaderComponent,
    ChildPopoverComponent
  ],
  entryComponents:[ 
    CommonModalComponent, 
    CommonPopoverComponent,
    ChildPopoverComponent
  ],
  imports: [
    CommonModule, FormsModule
  ],
  exports:[
    CommonHeaderComponent,
    CommonModalComponent,
    CommonPopoverComponent,
    SubpageHeaderComponent
  ],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class ComponentsModule { }
