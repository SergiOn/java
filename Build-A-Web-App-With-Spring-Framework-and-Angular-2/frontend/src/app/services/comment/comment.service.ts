import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Comment } from '../../models/comment';

@Injectable()
export class CommentService {

  constructor (private http: HttpClient) {}

  addComment(comment: Comment) {
    const tokenUrl1 = 'http://localhost:8080/rest/comment/add';
    const headers1 = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem('token')});
    console.log(JSON.stringify(comment));
    return this.http.post(tokenUrl1, comment, {headers: headers1});
  }

}
