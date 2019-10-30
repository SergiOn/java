import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  sendCredential(username: string, password: string) {
    const url = 'http://localhost:8181/token';
    const encodedCredentials = btoa(`${username}:${password}`);
    const basicHeader = `Basic ${encodedCredentials}`;
    const headers = new HttpHeaders ({
      'Content-Type': 'application/x-www-form-urlencoded',
      Authorization: basicHeader
    });

    return this.http.get(url, { headers });
  }

  checkSession() {
    const url = 'http://localhost:8181/checkSession';

    const headers = new HttpHeaders ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, { headers } );
  }

  logout() {
    const url = 'http://localhost:8181/user/logout';

    const headers = new HttpHeaders ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, '', { headers });
  }

}
