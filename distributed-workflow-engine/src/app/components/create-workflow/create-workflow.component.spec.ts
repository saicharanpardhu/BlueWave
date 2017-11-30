import { CreateWorkflowComponent } from "./create-workflow.component";
import { TestBed } from "@angular/core/testing";
import { async } from "@angular/core/testing";
import { ComponentFixture } from "@angular/core/testing";


describe("CreateWorkflowComponent", () => {
  let component: CreateWorkflowComponent;
  let fixture: ComponentFixture<CreateWorkflowComponent>;

  beforeEach(
    async(() => {
      TestBed.configureTestingModule({
        declarations: [
          CreateWorkflowComponent
        ],
      }).compileComponents();
    })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateWorkflowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
