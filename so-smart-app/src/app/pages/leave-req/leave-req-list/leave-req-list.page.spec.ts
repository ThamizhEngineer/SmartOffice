import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { LeaveReqListPage } from './leave-req-list.page';

describe('LeaveReqListPage', () => {
  let component: LeaveReqListPage;
  let fixture: ComponentFixture<LeaveReqListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveReqListPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(LeaveReqListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
