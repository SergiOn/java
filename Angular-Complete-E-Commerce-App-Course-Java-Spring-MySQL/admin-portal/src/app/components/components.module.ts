import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatGridListModule,
  MatInputModule, MatSelectModule,
  MatSlideToggleModule,
  MatToolbarModule
} from '@angular/material';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { AddNewBookComponent } from './add-new-book/add-new-book.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatToolbarModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    MatSelectModule,
    RouterModule
  ],
  declarations: [
    NavBarComponent,
    LoginComponent,
    AddNewBookComponent
  ],
  providers: [],
  exports: [
    NavBarComponent,
    LoginComponent
  ]
})
export class ComponentsModule { }
