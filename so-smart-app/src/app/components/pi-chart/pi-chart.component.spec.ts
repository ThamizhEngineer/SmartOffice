import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { PiChartComponent } from './pi-chart.component';

describe('PiChartComponent', () => {
  let component: PiChartComponent;
  let fixture: ComponentFixture<PiChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PiChartComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(PiChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
