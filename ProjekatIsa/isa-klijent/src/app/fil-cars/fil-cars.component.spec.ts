import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilCarsComponent } from './fil-cars.component';

describe('FilCarsComponent', () => {
  let component: FilCarsComponent;
  let fixture: ComponentFixture<FilCarsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilCarsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilCarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
