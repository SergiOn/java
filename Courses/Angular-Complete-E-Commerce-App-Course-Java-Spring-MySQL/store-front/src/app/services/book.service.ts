import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from '../constants/app-const';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book';

@Injectable()
export class BookService {

  constructor(private http: HttpClient) { }

  getBookList(): Observable<Book[]> {
    const url = `${AppConst.serverPath}/book/bookList`;

    const tokenHeader = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.get<Book[]>(url, {headers: tokenHeader});
  }

  getBook(id: number): Observable<Book> {
    const url = `${AppConst.serverPath}/book/${id}`;

    const tokenHeader = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.get<Book>(url, {headers: tokenHeader});
  }

  searchBook(keyword: string): Observable<Book[]> {
    const url = `${AppConst.serverPath}/book/searchBook`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post<Book[]>(url, keyword, { headers });
  }

}
