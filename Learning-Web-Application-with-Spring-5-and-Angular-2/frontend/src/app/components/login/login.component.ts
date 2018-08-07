import { Component } from '@angular/core';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public model = { 'username': '', 'password': '' };
  public currentUserName;

  constructor (private loginService: LoginService) {
    this.currentUserName = localStorage.getItem('currentUserName');
  }

  checkLogin() {
    return this.loginService.checkLogin();
  }

  onSubmit() {
    this.loginService.sendCredential(this.model).subscribe(
      (data) => {
        localStorage.setItem('token', data);

        this.loginService.sendToken(localStorage.getItem('token')).subscribe(
          (_data) => {
            this.currentUserName = this.model.username;

            localStorage.setItem('currentUserName', this.model.username);

            this.model.username = '';
            this.model.password = '';
          },
          (error) => console.log(error)
        );
      },
      (error) => console.log(error)
    );

  }

}
