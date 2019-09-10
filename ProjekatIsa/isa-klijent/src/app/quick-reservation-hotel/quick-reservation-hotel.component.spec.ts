import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuickReservationHotelComponent } from './quick-reservation-hotel.component';

describe('QuickReservationHotelComponent', () => {
  let component: QuickReservationHotelComponent;
  let fixture: ComponentFixture<QuickReservationHotelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuickReservationHotelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuickReservationHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
