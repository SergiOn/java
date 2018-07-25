import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationComponent } from './authentication/authentication.component';
import {AuthenticationService} from './authentication.service';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [AuthenticationService],
  exports: [AuthenticationComponent],
  declarations: [AuthenticationComponent]
})
export class LoginModule { }
