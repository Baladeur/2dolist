import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteWorkspaceDialogComponent } from './delete-workspace-dialog.component';

describe('DeleteWorkspaceDialogComponent', () => {
  let component: DeleteWorkspaceDialogComponent;
  let fixture: ComponentFixture<DeleteWorkspaceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeleteWorkspaceDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteWorkspaceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
