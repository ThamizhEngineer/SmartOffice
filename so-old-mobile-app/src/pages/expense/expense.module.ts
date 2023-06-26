import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExpensePage } from './expense.component';

import { Camera } from '@ionic-native/camera';
import { File } from '@ionic-native/file';
import { FileOpener } from '@ionic-native/file-opener';
import { ImagePicker } from '@ionic-native/image-picker';

@NgModule({
  declarations: [
    ExpensePage,
  ],
  imports: [
    IonicPageModule.forChild(ExpensePage),
  ],
  providers:[
    Camera,
    File,
    FileOpener,
    ImagePicker
  ]
})
export class ExpensePageModule {}
