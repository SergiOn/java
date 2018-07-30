import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Photo } from '../models/photo';
import { User } from '../models/user';

@Injectable()
export class PhotoService {

  constructor (private http: HttpClient) {
  }

  getPhotos() {
    const url = 'http://localhost:8080/photo/allPhotos';
    return this.http.get<Photo[]>(url);
  }

  // getPhotoById (photoId: number) {
  //   let tokenUrl1 = "http://localhost:8080/rest/photo/photoId";
  //   let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
  //   return this.http.post(tokenUrl1, JSON.stringify(photoId), {headers: headers1});
  // }

  // getPhotosByUser (user: User) {
  //   let tokenUrl1 = "http://localhost:8080/rest/photo/user";
  //   let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
  //   return this.http.post(tokenUrl1, JSON.stringify(user), {headers: headers1});
  // }

  // updatePhoto(photo: Photo) {
  //   let tokenUrl1 = "http://localhost:8080/rest/photo/update";
  //   let headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem("token")});
  //   return this.http.post(tokenUrl1, JSON.stringify(photo), {headers: headers1});
  // }

}
