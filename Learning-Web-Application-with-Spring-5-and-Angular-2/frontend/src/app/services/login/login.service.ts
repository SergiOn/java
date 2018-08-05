import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class LoginService {
  token: string;

  constructor (private http: HttpClient) {}

  sendCredential(model) {
    const tokenUrl1 = 'http://localhost:8080/user/login';
    const headers1 = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.post(tokenUrl1, model, { headers: headers1 });
  }

  sendToken(token) {
    const tokenUrl2 = 'http://localhost:8080/rest/user/users';
    console.log('Bearer ' + token);

    const getHeaders = new HttpHeaders({ 'Authorization': 'Bearer ' + token });

    return this.http.get(tokenUrl2, { headers: getHeaders });
  }

  logout() {
    localStorage.setItem('token', '');
    localStorage.setItem('currentUserName', '');
    alert('You just logged out.');
  }

  checkLogin() {
    const userLogged = localStorage.getItem('currentUserName') !== null
      && localStorage.getItem('currentUserName') !== ''
      && localStorage.getItem('token') !== null
      && localStorage.getItem('token') !== '';

    if (userLogged) {
      console.log(localStorage.getItem('currentUserName'));
      console.log(localStorage.getItem('token'));
      return true;
    } else {
      return false;
    }
  }

}
