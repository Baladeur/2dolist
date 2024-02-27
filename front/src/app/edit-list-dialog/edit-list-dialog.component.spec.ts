import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditListDialogComponent } from './edit-list-dialog.component';

describe('EditListDialogComponent', () => {
  let component: EditListDialogComponent;
  let fixture: ComponentFixture<EditListDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditListDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditListDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
