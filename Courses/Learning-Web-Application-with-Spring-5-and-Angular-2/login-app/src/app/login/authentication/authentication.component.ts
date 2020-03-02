import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css'],
})
export class AuthenticationComponent implements OnInit {

  public isLoggedIn: boolean = false;
  public connectionError: string = 'no errors';

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  public login(username: string, password: string): void {
    this.authenticationService.isLoggedIn(username, password).subscribe(
      (res: boolean) => this.isLoggedIn = res,
      (error: string) => this.connectionError = error
    );
  }

}
