
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {MatCardModule ,
  MatButtonModule, 
  MatFormFieldModule,  
  MatAutocompleteModule,
  MatToolbarModule, 
  MatExpansionModule, 
  MatGridListModule,
  MatInputModule,
  MatSelectModule,
  MatIconModule,
  MatTabsModule,
  MatSnackBarModule,
  MatProgressSpinnerModule,
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
    MatAutocompleteModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatProgressBarModule,
    MatExpansionModule,  
    MatGridListModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatIconModule,
    MatTabsModule,
    MatTableModule,
    MatDialogModule,
    MatStepperModule, 
    MatMenuModule
  ],
  exports: [
    MatButtonModule, 
    BrowserAnimationsModule, 
    MatToolbarModule,
    MatCardModule,
    MatExpansionModule,
    MatSnackBarModule, 
    MatGridListModule,
    MatProgressSpinnerModule,
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
    MatMenuModule
  ],
})
export class MaterialModule { }