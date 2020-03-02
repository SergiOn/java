import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable'
import {RequestOptions, Headers, Http} from '@angular/http';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {

  constructor(private http: Http) {
  }

  login(username: string, password: string): Observable<any> {
    let auth = 'Basic ' + btoa('acme:acmesecret');

    let headers = new Headers(
      {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Cache-Control': 'no-cache',
        'Authorization': auth
      }
    );

    let opt = new RequestOptions({headers: headers});

    return this.http.post('api/oauth/token?username='+username+
    '&password='+password+'&grant_type=password',{},opt)
      .map(response => <string[]> response.json())
  }

  logout(token: string): Observable<any> {
    let headers = new Headers({
        'Authorization': 'Bearer' + token
      }
    );

    let opt = new RequestOptions({headers: headers});

    return this.http.get('api/oauth/revoke-token',opt);
  }

  userInfo(token: string): Observable<any> {
    let headers = new Headers({
        'Authorization': 'Bearer' + token
      }
    );

    let opt = new RequestOptions({headers: headers});

    return this.http.get('api/private/user',opt).map(
      response => <string[]> response.json()
    );
  }
}
