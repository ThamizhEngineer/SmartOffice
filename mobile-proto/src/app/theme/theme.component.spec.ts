import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThemeComponent } from './theme.component';

describe('ThemePage', () => {
  let component: ThemeComponent;
  let fixture: ComponentFixture<ThemeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThemeComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
