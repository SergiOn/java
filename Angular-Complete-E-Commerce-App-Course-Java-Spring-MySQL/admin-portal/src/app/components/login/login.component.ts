import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public credential = { username: '', password: ''};
  public loggedIn = false;

  constructor(private loginService: LoginService) {
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      (res) => {
        this.loggedIn = true;
        console.log('LoginComponent: next', this.loggedIn);
        console.log('LoginComponent: next', res);
      },
      (error) => {
        this.loggedIn = false;
        console.log('LoginComponent: error', this.loggedIn);
        console.log('LoginComponent: error', error);
      }
    );
  }

  onSubmit(): void {
    this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
      res => {
        console.log(res);
        localStorage.setItem('xAuthToken', (res as any).token);
        this.loggedIn = true;
        // location.reload();
      },
      error => {
        console.log(error);
      }
    );
  }

}
