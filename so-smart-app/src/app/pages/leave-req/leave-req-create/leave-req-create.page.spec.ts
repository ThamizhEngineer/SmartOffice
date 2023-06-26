import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { LeaveReqCreatePage } from './leave-req-create.page';

describe('LeaveReqCreatePage', () => {
  let component: LeaveReqCreatePage;
  let fixture: ComponentFixture<LeaveReqCreatePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveReqCreatePage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(LeaveReqCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
