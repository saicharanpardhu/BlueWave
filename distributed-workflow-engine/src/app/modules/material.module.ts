
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {MatCardModule ,
  MatButtonModule, 
  MatFormFieldModule, 
  MatProgressSpinnerModule, 
  MatAutocompleteModule,
  MatToolbarModule, 
  MatExpansionModule, 
  MatGridListModule,
  MatInputModule,
  MatSelectModule,
  MatIconModule,
  MatTabsModule,
  MatSnackBarModule,
  MatTableModule,
  MatProgressBarModule,
  MatDialogModule,
  MatDialog,
  MatDialogRef,
  MatStepperModule,
  MatMenuModule
} from '@angular/material';

import { Validators } from '@angular/forms';
@NgModule({
  imports: [
    MatButtonModule, 
    BrowserAnimationsModule, 
    MatProgressSpinnerModule, 
    MatAutocompleteModule,
    MatToolbarModule,
    MatSnackBarModule,
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
    MatProgressSpinnerModule,
    MatMenuModule
  ],
  exports: [
    MatButtonModule, 
    BrowserAnimationsModule, 
    MatProgressSpinnerModule, 
    MatToolbarModule,
    MatCardModule,
    MatExpansionModule,
    MatSnackBarModule, 
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatTableModule,
    MatProgressBarModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatStepperModule,
    MatProgressSpinnerModule,
    MatMenuModule
  ],
})
export class MaterialModule { }