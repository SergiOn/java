import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from 'src/app/models/book';
import { Observable } from 'rxjs';

@Injectable()
export class EditBookService {

  constructor(private http: HttpClient) { }

  sendBook(book: Book): Observable<Book> {
    const url = 'http://localhost:8181/book/update';

    const headers = new HttpHeaders ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post<Book>(url, book, { headers });
  }
}
