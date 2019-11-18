import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from 'src/app/constants/app-const';
import { UserShipping } from 'src/app/models/user-shipping';
import { Observable } from 'rxjs';

@Injectable()
export class ShippingService {

  private serverPath: string = AppConst.serverPath;

  constructor(private http: HttpClient) { }

  newShipping(shipping: UserShipping) {
    const url = `${this.serverPath}/shipping/add`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, shipping, { headers });
  }

  getUserShippingList(): Observable<UserShipping[]> {
    const url = `${this.serverPath}/shipping/getUserShippingList`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.get<UserShipping[]>(url, { headers });
  }

  removeShipping(id: number) {
    const url = `${this.serverPath}/shipping/remove`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, id, { headers });
  }

  setDefaultShipping(id: number) {
    const url = `${this.serverPath}/shipping/setDefault`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, id, { headers });
  }

}
