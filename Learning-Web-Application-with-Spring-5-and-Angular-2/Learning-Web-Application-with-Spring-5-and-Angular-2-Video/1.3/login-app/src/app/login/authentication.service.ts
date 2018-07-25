import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {

  constructor() { }

  isLoggedIn(username: any) : boolean {
    if(username === 'Mario'){
      return true;
    }else{
      return false;
    }
  }
}
