import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ExpCreatePage } from './exp-create.page';

describe('ExpCreatePage', () => {
  let component: ExpCreatePage;
  let fixture: ComponentFixture<ExpCreatePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpCreatePage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ExpCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
