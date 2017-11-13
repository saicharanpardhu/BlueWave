import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowExecutionComponent } from './workflow-execution.component';

describe('WorkflowExecutionComponent', () => {
  let component: WorkflowExecutionComponent;
  let fixture: ComponentFixture<WorkflowExecutionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowExecutionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowExecutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
