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
import { BookListComponent } from './book-list/book-list.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    MatButtonModule,
    MatToolbarModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatSlideToggleModule,
    MatSelectModule
  ],
  declarations: [
    NavBarComponent,
    LoginComponent,
    AddNewBookComponent,
    BookListComponent
  ],
  providers: [],
  exports: [
    NavBarComponent,
    LoginComponent
  ]
})
export class ComponentsModule { }
