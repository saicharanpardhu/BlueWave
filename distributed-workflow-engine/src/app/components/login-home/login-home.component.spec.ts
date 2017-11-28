import { LoginHomeComponent } from "./../login-home/login-home.component";
import { async, ComponentFixture, TestBed } from "@angular/core/testing";
import { MaterialModule } from "../../modules/material.module";
import { AppRoutingModule } from "../../modules/app-routing.module";
import { BrowserModule } from "@angular/platform-browser";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { NgxChartsDagModule } from "@swimlane/ngx-charts-dag";
import { TagInputModule } from "ngx-chips";
import { Ng4JsonEditorModule } from "angular4-jsoneditor";
import { SocketService } from "../../services/socket/socket.service";
import { APP_BASE_HREF } from "@angular/common";
import { StompService } from "ng2-stomp-service";
import { ReportService } from "../../services/report/report.service";
import { AuthGuardService } from "../../services/authentication/auth-guard.service";
import { AuthenticationService } from "../../services/authentication/authentication.service";
import { AppConfig } from "../../app.config";

describe("LoginHomeComponent", () => {
  let component: LoginHomeComponent;
  let fixture: ComponentFixture<LoginHomeComponent>;
  class SocketServiceStub extends SocketService{
    
  }
  beforeEach(
    async(() => {
      TestBed.configureTestingModule({
        declarations: [ 
          LoginHomeComponent, 
          LoginHomeComponent
        ],
        imports: [
          BrowserModule,
          AppRoutingModule,
          MaterialModule,
          ReactiveFormsModule,
          HttpModule,
          FormsModule,
          NgxChartsModule,
          NgxChartsDagModule,
          TagInputModule,
          Ng4JsonEditorModule
        ],
        providers: [
          {provide: SocketService, useValue: SocketServiceStub },
          AppConfig,
          AuthGuardService, 
          AuthenticationService,
          { provide: APP_BASE_HREF, useValue: "/" }
        ]
      }).compileComponents();
    })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should be created", () => {
    expect(component).toBeTruthy();
  });
});
