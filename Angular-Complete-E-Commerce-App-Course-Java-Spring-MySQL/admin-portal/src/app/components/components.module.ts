import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatToolbarModule } from '@angular/material';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from '../services/login.service';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatToolbarModule
  ],
  declarations: [
    NavBarComponent,
    LoginComponent
  ],
  providers: [
    LoginService
  ],
  exports: [
    NavBarComponent
  ]
})
export class ComponentsModule { }
