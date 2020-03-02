import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'frontend';

  res$: Observable<any> = this.getUser();

  users$: Observable<any> = this.getUser().pipe(
    map((res: any) => res._embedded.user)
  );

  constructor(private http: HttpClient) {}

  getUser(): Observable<any> {
    return this.http.get('/api/user')
  }

}
