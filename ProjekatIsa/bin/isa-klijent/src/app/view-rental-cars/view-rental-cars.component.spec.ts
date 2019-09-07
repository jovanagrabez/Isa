import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRentalCarsComponent } from './view-rental-cars.component';

describe('ViewRentalCarsComponent', () => {
  let component: ViewRentalCarsComponent;
  let fixture: ComponentFixture<ViewRentalCarsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewRentalCarsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRentalCarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
