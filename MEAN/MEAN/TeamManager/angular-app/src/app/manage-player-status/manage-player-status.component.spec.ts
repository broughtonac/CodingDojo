import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagePlayerStatusComponent } from './manage-player-status.component';

describe('ManagePlayerStatusComponent', () => {
  let component: ManagePlayerStatusComponent;
  let fixture: ComponentFixture<ManagePlayerStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagePlayerStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagePlayerStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
