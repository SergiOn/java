import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationComponent } from './authentication/authentication.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [AuthenticationComponent],
  declarations: [AuthenticationComponent]
})
export class LoginModule { }
