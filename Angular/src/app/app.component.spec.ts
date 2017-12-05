import { TestBed, async,  ComponentFixture } from '@angular/core/testing';
import { By, BrowserModule }              from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { DebugElement }    from '@angular/core'; 
import { MaterialModule } from './modules/material.module';
import { AppRoutingModule } from './modules/app-routing.module';
import { HttpModule } from '@angular/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NgxChartsDagModule } from '@swimlane/ngx-charts-dag';
import { TagInputModule } from 'ngx-chips';
import { Ng4JsonEditorModule } from 'angular4-jsoneditor';
import { APP_BASE_HREF } from '@angular/common';
import { AppConfig } from './app.config';
describe('AppComponent', () => {

  let comp:    AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let de:      DebugElement;
  let el:      HTMLElement; 
  let spy; 
 
  
  beforeEach(async(() => {
 
    TestBed.configureTestingModule({
      declarations: [
        AppComponent, 
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
      providers:    [  
        {provide: APP_BASE_HREF, useValue : '/' } ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);  
    comp = fixture.componentInstance; 
  });

  it('should create the app', async(() => { 
    expect(comp).toBeTruthy();
  }));

  it(`should have as title 'app'`, async(() => { 
    expect(comp.title).toEqual('app');
  }));

  it(`should establish socket connection`, async(() => {  

  }));

});

