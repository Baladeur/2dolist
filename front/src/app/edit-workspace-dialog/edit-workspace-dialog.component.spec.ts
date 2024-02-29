import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditWorkspaceDialogComponent } from './edit-workspace-dialog.component';

describe('EditWorkspaceDialogComponent', () => {
  let component: EditWorkspaceDialogComponent;
  let fixture: ComponentFixture<EditWorkspaceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditWorkspaceDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditWorkspaceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
