import { HomeComponent } from './components/home/home.component';
import { AuthenticationService } from './services/authentication/authentication.service';
import { TestBed, async,  ComponentFixture } from '@angular/core/testing';
import { By, BrowserModule }              from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { DebugElement }    from '@angular/core'; 
import { SocketService } from './services/socket/socket.service';
import { MaterialModule } from './modules/material.module';
import { AppRoutingModule } from './modules/app-routing.module';
import { HttpModule } from '@angular/http';
import { WorkflowComponent } from './components/workflow/workflow.component';
import { WorkflowExecutionComponent } from './components/workflow-execution/workflow-execution.component';
import { GetReportComponent } from './components/reports/reports.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginHomeComponent } from './components/login-home/login-home.component'; 
import { CreateWorkflowComponent, DialogOverviewDialog, WnameOverviewDialog, JsonEditor, SettingsDialog } from './components/create-workflow/create-workflow.component';
import { ExecuteWorkflowComponent } from './components/execute-workflow/execute-workflow.component';
import { WorkflowDetailsComponent } from './components/workflow-details/workflow-details.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxChartsDagModule } from '@swimlane/ngx-charts-dag';
import { TagInputModule } from 'ngx-chips';
import { Ng4JsonEditorModule } from 'angular4-jsoneditor';
import { APP_BASE_HREF } from '@angular/common';
import { AuthGuardService } from './services/authentication/auth-guard.service';
import { WorkflowDetailsService } from './services/workflow-details/workflow-details.service';
import { PerisitenceService } from './services/persistence/perisitence.service';
import { StompService } from 'ng2-stomp-service';
import { ReportService } from './services/report/report.service';
describe('AppComponent', () => {

  let comp:    AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let de:      DebugElement;
  let el:      HTMLElement;
  let authenticaionService;
  let spy;
  let socketSerivce;
  beforeEach(async(() => {

    let AuthenticationServiceStub = {
      isLoggedIn: true,
      user: { name: 'Test User'}
    };
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        HomeComponent,
        WorkflowComponent,
        WorkflowExecutionComponent,
        FooterComponent,
        NavbarComponent,
        ProfileComponent,
        LoginHomeComponent,
        CreateWorkflowComponent,
        DialogOverviewDialog,
        WnameOverviewDialog,
        ExecuteWorkflowComponent ,
        WorkflowDetailsComponent,
        JsonEditor,SettingsDialog,GetReportComponent
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
      providers:    [ SocketService, WorkflowDetailsService, PerisitenceService, AuthGuardService, SocketService, StompService, ReportService, {provide: AuthenticationService, useValue: AuthenticationServiceStub },
        {provide: APP_BASE_HREF, useValue : '/' } ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent); 
    socketSerivce  = fixture.debugElement.injector.get(SocketService);
    spy = spyOn(socketSerivce, 'connect').and.returnValue(Promise.resolve()); 
    comp = fixture.componentInstance; // BannerComponent test instance
    authenticaionService = TestBed.get(AuthenticationService);
    // query for the title <h1> by CSS element selector
    de = fixture.debugElement.query(By.css('h1'));
    el = de.nativeElement;
  });

  it('should create the app', async(() => { 
    expect(comp).toBeTruthy();
  }));

  it(`should have as title 'app'`, async(() => { 
    expect(comp.title).toEqual('app');
  }));

  it(`should establish socket connection`, async(() => { 
    expect(spy.calls.any()).toBe(true);
  }));

});
