import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from '../constants/app-const';
import { Observable } from 'rxjs';
import { CartItem } from 'src/app/models/cart-item';
import { ShoppingCart } from 'src/app/models/shopping-cart';

@Injectable()
export class CartService {

  constructor(private http: HttpClient) { }

  addItem(id: number, qty: number) {
    const url = `${AppConst.serverPath}/cart/add`;
    const cartItemInfo = {
      bookId: id,
      qty
    };
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, cartItemInfo, { headers });
  }

  getCartItemList(): Observable<CartItem[]> {
    const url = `${AppConst.serverPath}/cart/getCartItemList`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.get<CartItem[]>(url, { headers });
  }

  getShoppingCart(): Observable<ShoppingCart> {
    const url = `${AppConst.serverPath}/cart/getShoppingCart`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.get<ShoppingCart>(url, { headers });
  }

  updateCartItem(cartItemId: number, qty: number) {
    const url = `${AppConst.serverPath}/cart/updateCartItem`;
    const cartItemInfo = {
      cartItemId,
      qty
    };
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, cartItemInfo, { headers });
  }

  removeCartItem(id: number) {
    const url = `${AppConst.serverPath}/cart/removeItem`;

    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });
    return this.http.post(url, id, { headers });
  }

}
