import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css'],
})
export class AuthenticationComponent implements OnInit {

  public isLoggedIn: boolean = false;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  public login(username: string, password: string): void {
    this.isLoggedIn = this.authenticationService.isLoggedIn(username, password);
  }

}
