import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {

  constructor() { }

  isLoggedIn(username: string, password: string) : boolean {
    if(username === 'Mario' && password === 'secret'){
      return true;
    }else{
      return false;
    }
  }
}
