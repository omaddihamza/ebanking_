import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAccountsComponent } from './list-accounts.component';

describe('ListAccountsComponent', () => {
  let component: ListAccountsComponent;
  let fixture: ComponentFixture<ListAccountsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListAccountsComponent]
    });
    fixture = TestBed.createComponent(ListAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
