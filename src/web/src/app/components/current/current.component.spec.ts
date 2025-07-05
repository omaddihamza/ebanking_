import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentComponent } from './current.component';

describe('CurrentComponent', () => {
  let component: CurrentComponent;
  let fixture: ComponentFixture<CurrentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CurrentComponent]
    });
    fixture = TestBed.createComponent(CurrentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
