import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {

  constructor() { }

  public isLoggedIn(username: string): boolean {
    if (username === 'Mario') {
      return true;
    } else {
      return false;
    }
  }

}
