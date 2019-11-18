import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from 'src/app/constants/app-const';
import { Observable } from 'rxjs';
import { Order } from 'src/app/models/order';

@Injectable()
export class OrderService {

  constructor(private http: HttpClient) { }

  getOrderList(): Observable<Order[]> {
    const url = `${AppConst.serverPath}/order/getOrderList`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.get<Order[]>(url, { headers });
  }

}
