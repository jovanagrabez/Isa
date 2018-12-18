import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeGuestSearchComponent } from './home-guest-search.component';

describe('HomeGuestSearchComponent', () => {
  let component: HomeGuestSearchComponent;
  let fixture: ComponentFixture<HomeGuestSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeGuestSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeGuestSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
