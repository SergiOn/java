import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login/login.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  myLocalStorage;

  constructor(private loginService: LoginService) {
    this.myLocalStorage = localStorage;
  }

  checkLogin() {
    return this.loginService.checkLogin();
  }

  onClick() {
    if (this.checkLogin()) {
      this.loginService.logout();
    }
  }
}
