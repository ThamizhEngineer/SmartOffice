import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonModalComponent } from './common-modal.component';

describe('CommonModalPage', () => {
  let component: CommonModalComponent;
  let fixture: ComponentFixture<CommonModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonModalComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
