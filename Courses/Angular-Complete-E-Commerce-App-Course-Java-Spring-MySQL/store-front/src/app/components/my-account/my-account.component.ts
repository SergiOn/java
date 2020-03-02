import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppConst } from 'src/app/constants/app-const';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.scss']
})
export class MyAccountComponent implements OnInit {

  private serverPath = AppConst.serverPath;
  public loginError = false;
  private loggedIn = false;
  public credential = { username: '', password: ''};

  public emailSent = false;
  public usernameExists: boolean;
  public emailExists: boolean;
  public username: string;
  public email: string;

  public emailNotExists = false;
  public forgetPasswordEmailSent: boolean;
  public recoverEmail: string;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        console.log(res);
        this.loggedIn = true;
      },
      error => {
        console.log(error);
        this.loggedIn = false;
      }
    );
  }

  onLogin() {
    this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
      res => {
        console.log(res);
        localStorage.setItem('xAuthToken', (res as any).token);
        this.loggedIn = true;
        location.reload();
        this.router.navigate(['/home']);
      },
      error => {
        this.loggedIn = false;
        this.loginError = true;
      }
    );
  }

  onNewAccount() {
    this.usernameExists = false;
    this.emailExists = false;
    this.emailSent = false;

    this.userService.newUser(this.username, this.email).subscribe(
      res => {
        console.log(res);
        this.emailSent = true;
      },
      error => {
        console.log(error);
        const errorMessage = error;
        if (errorMessage === 'usernameExists') {
          this.usernameExists = true;
        }
        if (errorMessage === 'emailExists') {
          this.emailExists = true;
        }
      }
    );
  }

  onForgetPassword() {
    this.forgetPasswordEmailSent = false;
    this.emailNotExists = false;

    this.userService.retrievePassword(this.recoverEmail).subscribe(
      res => {
        console.log(res);
        this.forgetPasswordEmailSent = true;
      },
      error => {
        console.log(error);
        if (error === 'Email not found') {
          this.emailNotExists = true;
        }
      }
    );
  }

}
