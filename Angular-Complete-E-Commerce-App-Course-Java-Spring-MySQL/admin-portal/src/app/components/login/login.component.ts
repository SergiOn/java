import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private credential = { username: '', password: ''};
  private loggedIn = false;

  constructor(private loginService: LoginService) {
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn = true;
      },
      error => {
        this.loggedIn = false;
      }
    );
  }

  onSubmit(): void {
    this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
      res => {
        console.log(res);
        localStorage.setItem('xAuthToken', (res as any).token);
        this.loggedIn = true;
        location.reload();
      },
      error => {
        console.log(error);
      }
    );
  }

}
