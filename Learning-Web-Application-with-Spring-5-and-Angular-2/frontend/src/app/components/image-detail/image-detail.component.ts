import { Component } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../../services/user/user.service';
import { PhotoService } from '../../services/photo/photo.service';
import { Photo } from '../../models/photo';
import { User } from '../../models/user';

@Component({
  selector: 'image-detail',
  templateUrl: './image-detail.component.html',
  styleUrls: ['./image-detail.component.css']
})
export class ImageDetailComponent {

  photo: Photo = new Photo();
  like: string;
  user: User;
  photoId: number;

  constructor (private route: ActivatedRoute,
               private location: Location,
               private photoService: PhotoService,
               private userService: UserService) {

    this.photoId = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    // this.route.params.forEach((params: Params) => {
    //   this.photoId = Number.parseInt(params['id']);
    // });


    this.photoService.getPhotoById(this.photoId).subscribe(
      (photo: Photo) => {
        this.photo = photo;

        this.userService.getUserByName(localStorage.getItem('currentUserName')).subscribe(
          (user: User) => {
            this.user = user;
            const photoLiked = this.user.likedPhotoList.findIndex((item: Photo) => item.photoId === this.photo.photoId);

            if (photoLiked !== -1) {
              this.like = 'Unlike';
            } else {
              this.like = 'Like';
            }
          },
          error => console.log(error)
        );
      },
      error => console.log(error)
    );
  }

  goBack() {
    this.location.back();
    // window.history.back();
  }

  likeDisplay() {
    if (this.like === 'Like') {
      this.like = 'Unlike';
      this.user.likedPhotoList.push(this.photo);
      this.photo.likes += 1;
      this.userService.updateUser(this.user).subscribe();
      this.photoService.updatePhoto(this.photo).subscribe();
    } else {
      this.like = 'Like';
      // var index = this.user.likedPhotoList.indexOf(this.photo, 0);
      // for (let i = 0; i < this.user.likedPhotoList.length; i++) {
      //   if (this.user.likedPhotoList[i].photoId === this.photo.photoId) {
      //     this.user.likedPhotoList.splice(i, 1);
      //   }
      // }
      // console.log(index);
      // if (index > -1) {
      //   this.user.likedPhotoList.splice(index, 1);
      // }
      this.user.likedPhotoList = this.user.likedPhotoList.filter((item: Photo) => item.photoId !== this.photo.photoId);
      this.photo.likes -= 1;
      this.userService.updateUser(this.user).subscribe();
      this.photoService.updatePhoto(this.photo).subscribe();
    }
  }

}
