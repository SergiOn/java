import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PhotoService } from '../../services/photo/photo.service';
import { UserService } from '../../services/user/user.service';
import { Photo } from '../../models/photo';
import { User } from '../../models/user';

@Component({
  selector: 'my-album',
  templateUrl: './my-album.component.html',
  styleUrls: ['./my-album.component.css']
})
export class MyAlbumComponent {

  public photos: Photo[];
  private user;
  private selectedPhoto: Photo;

  constructor (private router: Router,
               private userService: UserService,
               private photoService: PhotoService) {

    this.userService.getUserByName(localStorage.getItem('currentUserName')).subscribe(
      (user: User) => {
        this.user = user;

        console.log(this.user);
/*
        this.photoService.getPhotosByUser(this.user).subscribe(
          (photos)  => {
            console.log(this.photos = user.photoList);
          },
          (error) => console.log(error)
        );
*/
      },
      error => console.log(error)
    );
  }

  onSelect(photo: Photo) {
    this.selectedPhoto = photo;
    this.router.navigate(['/image-detail', this.selectedPhoto.photoId]);

  }

}
