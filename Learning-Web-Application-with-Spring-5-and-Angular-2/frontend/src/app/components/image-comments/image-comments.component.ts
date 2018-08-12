import { Component, Input } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { PhotoService } from '../../services/photo/photo.service';
import { CommentService } from '../../services/comment/comment.service';
import { Comment } from '../../models/comment';
import { Photo } from '../../models/photo';
import { User } from '../../models/user';

@Component({
  selector: 'image-comments',
  templateUrl: './image-comments.component.html',
  styleUrls: ['./image-comments.component.css']
})
export class ImageCommentsComponent {

  @Input() photo: Photo;

  myLocalStorage = localStorage;
  user: User = new User();
  newComment: Comment = new Comment();

  constructor (private userService: UserService, private commentService: CommentService, private photoService: PhotoService) {
    this.userService.getUserByName(localStorage.getItem('currentUserName')).subscribe(
      (user: User) => {
        this.user = user;
      },
      error => console.log(error)
    );
  }

  onSubmit() {
    console.log(this.photo);
    console.log(this.photo.commentList);
    this.newComment.photo = this.photo;
    this.newComment.userName = this.user.userName;
    this.newComment.photoId = this.photo.photoId;

    console.log('newComment', this.newComment);

    this.commentService.addComment(this.newComment).subscribe(
      (_photo: Photo) => this.photoService.getPhotoById(this.photo.photoId)
        .subscribe(
          (photo: Photo) => this.photo = photo,
        error => console.log(error)
      )
      // error => console.log(error)
    );
    // this.photo.commentList.push(this.newComment);


    this.newComment = new Comment();
  }

}
