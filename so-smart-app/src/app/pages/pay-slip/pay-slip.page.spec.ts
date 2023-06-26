import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { PaySlipPage } from './pay-slip.page';

describe('PaySlipPage', () => {
  let component: PaySlipPage;
  let fixture: ComponentFixture<PaySlipPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaySlipPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(PaySlipPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
