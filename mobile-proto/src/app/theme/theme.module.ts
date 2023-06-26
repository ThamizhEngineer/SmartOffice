import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { ThemeRoutes } from "./theme.routing";
import { ThemeComponent } from "./theme.component";

import { IonicModule } from '@ionic/angular';
import { ComponentsModule } from "../components/components.module";
import { TabsService } from '../services/tabs.service';

import { AppVersion } from '@ionic-native/app-version/ngx';


@NgModule({
   declarations:[
    ThemeComponent
   ],
   imports: [
    CommonModule,
    IonicModule,
    RouterModule.forChild(ThemeRoutes),
    ComponentsModule
  ],
  providers:[
    AppVersion,
    TabsService
  ],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]

})

export class ThemeModule {}