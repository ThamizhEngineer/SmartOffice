import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TarListPage } from './tar-list.page';

describe('TarListPage', () => {
  let component: TarListPage;
  let fixture: ComponentFixture<TarListPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarListPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TarListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
