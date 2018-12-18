import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAvioCompaniesComponent } from './view-avio-companies.component';

describe('ViewAvioCompaniesComponent', () => {
  let component: ViewAvioCompaniesComponent;
  let fixture: ComponentFixture<ViewAvioCompaniesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAvioCompaniesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAvioCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
