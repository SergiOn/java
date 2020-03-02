import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConst } from '../constants/app-const';
import { ShippingAddress } from '../models/shipping-address';
import { BillingAddress } from '../models/billing-address';
import { Payment } from '../models/payment';
import { Order } from '../models/order';

@Injectable()
export class CheckoutService {

  constructor(private http: HttpClient) { }

  checkout(
    shippingAddress: ShippingAddress,
    billingAddress: BillingAddress,
    payment: Payment,
    shippingMethod: string
  ): Observable<Order> {
    const url = `${AppConst.serverPath}/checkout/checkout`;
    const order = {
      shippingAddress,
      billingAddress,
      payment,
      shippingMethod
    };

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post<Order>(url, order, { headers });
  }

  getUserOrder() {
    const url = `${AppConst.serverPath}/checkout/getUserOrder`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.get(url, { headers });
  }

}
