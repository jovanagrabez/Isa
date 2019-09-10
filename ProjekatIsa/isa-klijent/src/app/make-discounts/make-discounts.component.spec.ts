import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeDiscountsComponent } from './make-discounts.component';

describe('MakeDiscountsComponent', () => {
  let component: MakeDiscountsComponent;
  let fixture: ComponentFixture<MakeDiscountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MakeDiscountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeDiscountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
