import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ExpListPage } from './exp-list.page';

describe('ExpListPage', () => {
  let component: ExpListPage;
  let fixture: ComponentFixture<ExpListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpListPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ExpListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
