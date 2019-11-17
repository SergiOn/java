import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from '../constants/app-const';

@Injectable()
export class LoginService {

  private serverPath: string = AppConst.serverPath;

  constructor(private http: HttpClient, private router: Router) { }

  sendCredential(username: string, password: string) {
    const url = `${this.serverPath}/token`;
    const encodedCredentials = btoa(`${username}:${password}`);
    const basicHeader = `Basic ${encodedCredentials}`;
    const headers = new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded',
      Authorization : basicHeader
    });

    return this.http.get(url, { headers });
  }

  checkSession() {
    const url = `${this.serverPath}/checkSession`;
    const headers = new HttpHeaders ({
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.get(url, { headers, responseType: 'text' } );
  }

  logout() {
    const url = `${this.serverPath}/user/logout`;
    const headers = new HttpHeaders({
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.post(url, '', { headers, responseType: 'text' });
  }

}
