import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SlidesComponent } from './slides/slides.component';
import { StartComponent } from './start/start.component';
import { LogoComponent } from './logo/logo.component';
import { IonicModule } from '@ionic/angular';
import { PiChartComponent } from './pi-chart/pi-chart.component';
import {  NO_ERRORS_SCHEMA } from '@angular/core';
import { SeriesPipe } from './pi-chart/series.pipe';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [SlidesComponent,StartComponent,LogoComponent,PiChartComponent,SeriesPipe],
  exports:[SlidesComponent,StartComponent,LogoComponent,PiChartComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    CommonModule
  ],
  schemas: [ NO_ERRORS_SCHEMA],

})
export class ComponentsModule { }
