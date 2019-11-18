import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from 'src/app/constants/app-const';
import { UserPayment } from 'src/app/models/user-payment';
import { Observable } from 'rxjs';

@Injectable()
export class PaymentService {

  private serverPath: string = AppConst.serverPath;

  constructor(private http: HttpClient) { }

  newPayment(payment: UserPayment) {
    const url = `${this.serverPath}/payment/add`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, payment, { headers });
  }

  getUserPaymentList(): Observable<UserPayment[]> {
    const url = `${this.serverPath}/payment/getUserPaymentList`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.get<UserPayment[]>(url,  { headers });
  }

  removePayment(id: number) {
    const url = `${this.serverPath}/payment/remove`;

    const tokenHeader = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, id, {headers: tokenHeader});
  }

  setDefaultPayment(id: number) {
    const url = `${this.serverPath}/payment/setDefault`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, id, { headers });
  }
}
