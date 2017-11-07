
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {MatCardModule ,
  MatButtonModule, 
  MatFormFieldModule, 
  MatProgressSpinnerModule, 
  MatToolbarModule, 
  MatExpansionModule, 
  MatGridListModule,
  MatInputModule,
  MatSelectModule,
  MatIconModule,
  MatTabsModule,
  MatTableModule,
  MatProgressBarModule,
  MatDialogModule,
  MatDialog,
  MatDialogRef,
  MatStepperModule
} from '@angular/material';

import { Validators } from '@angular/forms';
@NgModule({
  imports: [
    MatButtonModule, 
    BrowserAnimationsModule, 
    MatProgressSpinnerModule, 
    MatToolbarModule,
    MatProgressBarModule,
    MatExpansionModule,  
    MatGridListModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatTableModule,
    MatDialogModule,
    MatStepperModule,
    MatProgressSpinnerModule
  ],
  exports: [
    MatButtonModule, 
    BrowserAnimationsModule, 
    MatProgressSpinnerModule, 
    MatToolbarModule,
    MatCardModule,
    MatExpansionModule, 
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatTableModule,
    MatProgressBarModule,
    MatDialogModule,
    MatStepperModule,
    MatProgressSpinnerModule
  ],
})
export class MaterialModule { }