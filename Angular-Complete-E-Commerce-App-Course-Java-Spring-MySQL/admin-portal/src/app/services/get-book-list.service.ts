import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book';

@Injectable()
export class GetBookListService {

  constructor(private http: HttpClient) { }

  getBookList(): Observable<Book[]> {
    const url = 'http://localhost:8181/book/bookList';
    const headers = new HttpHeaders ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get<Book[]>(url, { headers });
  }

}
