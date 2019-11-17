import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConst } from '../constants/app-const';
import { User } from '../models/user';

@Injectable()
export class UserService {

  private serverPath: string = AppConst.serverPath;

  constructor(private http: HttpClient) { }

  newUser(username: string, email: string) {
    const url = `${this.serverPath}/user/newUser`;
    const userInfo = {
      username,
      email
    };
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.post(url, userInfo, { headers });
  }

  updateUserInfo(user: User, newPassword: string, currentPassword: string) {
    const url = `${this.serverPath}/user/updateUserInfo`;
    const userInfo = {
      id: user.id,
      firstName: user.firstName,
      lastName: user.lastName,
      username: user.username,
      email: user.email,
      currentPassword,
      newPassword
    };
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.post(url, userInfo, { headers });
  }

  retrievePassword(email: string) {
    const url = `${this.serverPath}/user/forgetPassword`;
    const userInfo = {
      email
    };
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.post(url, userInfo, { headers });
  }

  getCurrentUser() {
    const url = `${this.serverPath}/user/getCurrentUser`;
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken') || ''
    });

    return this.http.get(url, { headers });
  }

}
