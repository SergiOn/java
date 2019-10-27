import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatToolbarModule } from '@angular/material';
import { NavBarComponent } from './nav-bar/nav-bar.component';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatToolbarModule
  ],
  declarations: [
    NavBarComponent
  ],
  providers: [],
  exports: [
    NavBarComponent
  ]
})
export class ComponentsModule { }
