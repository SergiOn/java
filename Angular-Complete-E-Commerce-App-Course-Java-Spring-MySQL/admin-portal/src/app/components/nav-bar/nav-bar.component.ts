import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  public loggedIn: boolean = false;

  constructor(private router: Router, private loginService: LoginService) {
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      (res) => {
        this.loggedIn = true;
        console.log('next', this.loggedIn);
        console.log('NavBarComponent: next', res);
      },
      (error) => {
        this.loggedIn = false;
        console.log('error', this.loggedIn);
        console.log('NavBarComponent: error', error);
      }
    );
  }

  logout() {
    this.loginService.logout().subscribe(
      res => {
        location.reload();
      },
      error => {
        console.log(error);
      }
    );

    this.router.navigate(['/']);
  }

}
