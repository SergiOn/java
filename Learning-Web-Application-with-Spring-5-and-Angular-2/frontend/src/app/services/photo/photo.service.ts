import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Photo } from '../../models/photo';
import { User } from '../../models/user';

@Injectable()
export class PhotoService {

  constructor (private http: HttpClient) {
  }

  getPhotos() {
    const url = 'http://localhost:8080/photo/allPhotos';
    return this.http.get<Photo[]>(url);
  }

  getPhotoById(photoId: number) {
    const tokenUrl1 = 'http://localhost:8080/rest/photo/photoId';
    const headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem('token')});
    return this.http.post(tokenUrl1, photoId, {headers: headers1});
  }

  getPhotosByUser(user: User) {
    const tokenUrl1 = 'http://localhost:8080/rest/photo/user';
    const headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem('token')});
    return this.http.post(tokenUrl1, user, {headers: headers1});
  }

  updatePhoto(photo: Photo) {
    const tokenUrl1 = 'http://localhost:8080/rest/photo/update';
    const headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem('token')});
    return this.http.post(tokenUrl1, photo, {headers: headers1});
  }

}
