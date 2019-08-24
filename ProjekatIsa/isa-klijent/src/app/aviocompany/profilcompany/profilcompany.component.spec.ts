import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilcompanyComponent } from './profilcompany.component';

describe('ProfilcompanyComponent', () => {
  let component: ProfilcompanyComponent;
  let fixture: ComponentFixture<ProfilcompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilcompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilcompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
