import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFilijaleComponent } from './add-filijale.component';

describe('AddFilijaleComponent', () => {
  let component: AddFilijaleComponent;
  let fixture: ComponentFixture<AddFilijaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFilijaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFilijaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
