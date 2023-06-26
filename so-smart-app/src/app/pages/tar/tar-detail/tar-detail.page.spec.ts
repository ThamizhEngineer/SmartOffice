import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TarDetailPage } from './tar-detail.page';

describe('TarDetailPage', () => {
  let component: TarDetailPage;
  let fixture: ComponentFixture<TarDetailPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TarDetailPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TarDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
