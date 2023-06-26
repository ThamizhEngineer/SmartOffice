import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TarCreatePage } from './tar-create.page';

describe('TarCreatePage', () => {
  let component: TarCreatePage;
  let fixture: ComponentFixture<TarCreatePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarCreatePage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TarCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
