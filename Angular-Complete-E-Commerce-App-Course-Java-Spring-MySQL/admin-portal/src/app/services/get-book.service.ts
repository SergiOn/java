import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from 'src/app/models/book';
import { Observable } from 'rxjs';

@Injectable()
export class GetBookService {

  constructor(private http: HttpClient) { }

  getBook(id: number): Observable<Book> {
    const url = `http://localhost:8181/book/${id}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get<Book>(url, { headers });
  }

}
