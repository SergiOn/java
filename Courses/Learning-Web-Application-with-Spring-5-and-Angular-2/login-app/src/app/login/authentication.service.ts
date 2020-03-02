import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  public isLoggedIn(username: string, password: string): Observable<boolean> {
    const headers = new HttpHeaders({ 'Content-Type': 'X-custom' });

    return this.http.get('/assets/login.json', { headers }).pipe(
      map((res: any) => res.username === username && res.password === password),
      catchError((error: HttpErrorResponse) => throwError(error.statusText))
    );
  }

}
