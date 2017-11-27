import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MaterialModule } from '../../modules/material.module';
import { AppRoutingModule } from '../../modules/app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxChartsDagModule } from '@swimlane/ngx-charts-dag';
import { TagInputModule } from 'ngx-chips';
import { Ng4JsonEditorModule } from 'angular4-jsoneditor';
import { SocketService } from '../../services/socket/socket.service';
import { WorkflowDetailsService } from '../../services/workflow-details/workflow-details.service';
import { PerisitenceService } from '../../services/persistence/perisitence.service';
import { APP_BASE_HREF } from '@angular/common';
import { StompService } from 'ng2-stomp-service';
import { ReportService } from '../../services/report/report.service';
import { AuthGuardService } from '../../services/authentication/auth-guard.service';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { AppConfig } from '../../app.config';
import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  class SocketServiceStub extends SocketService{
    
  }
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ 
      HomeComponent
     ],
      imports: [
        BrowserModule,  
        AppRoutingModule,
        MaterialModule, 
        ReactiveFormsModule, 
        HttpModule,
        FormsModule, 
      ],
      providers:   [  WorkflowDetailsService,  AuthenticationService,
        {provide: APP_BASE_HREF, useValue : '/' } ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('should have width 1000', () => {
    expect(component.view[0]).toBe(1000);
  });
});
