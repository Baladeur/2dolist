import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkspaceDialogComponent } from './add-workspace-dialog.component';

describe('AddWorkspaceDialogComponent', () => {
  let component: AddWorkspaceDialogComponent;
  let fixture: ComponentFixture<AddWorkspaceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddWorkspaceDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddWorkspaceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
