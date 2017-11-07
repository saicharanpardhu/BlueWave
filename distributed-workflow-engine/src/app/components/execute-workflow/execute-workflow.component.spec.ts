import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecuteWorkflowComponent } from './execute-workflow.component';

describe('ExecuteWorkflowComponent', () => {
  let component: ExecuteWorkflowComponent;
  let fixture: ComponentFixture<ExecuteWorkflowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExecuteWorkflowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExecuteWorkflowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
