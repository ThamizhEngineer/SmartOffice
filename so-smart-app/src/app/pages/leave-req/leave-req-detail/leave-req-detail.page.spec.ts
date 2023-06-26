import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { LeaveReqDetailPage } from './leave-req-detail.page';

describe('LeaveReqDetailPage', () => {
  let component: LeaveReqDetailPage;
  let fixture: ComponentFixture<LeaveReqDetailPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveReqDetailPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(LeaveReqDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
