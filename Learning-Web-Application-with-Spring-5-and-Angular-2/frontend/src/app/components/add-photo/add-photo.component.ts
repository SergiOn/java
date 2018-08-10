import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { AddPhotoService } from '../../services/add-photo/add-photo.service';
import { UploadPhotoService } from '../../services/upload-photo/upload-photo.service';
import { Photo } from '../../models/photo';
import { User } from '../../models/user';

@Component({
  selector: 'add-photo',
  templateUrl: './add-photo.component.html',
  styleUrls: ['./add-photo.component.css']
})
export class AddPhotoComponent {

  newPhoto: Photo = new Photo();
  photoAdded: boolean = false;
  user: User;

  constructor (private uploadPhotoService: UploadPhotoService,
               private addPhotoService: AddPhotoService,
               private userService: UserService) {
  }

  public fileChangeEvent($event: Event) {
    this.uploadPhotoService.fileChangeEvent($event);
  }

  public upload() {
    this.uploadPhotoService.upload();
  }

  onSubmit() {
    this.userService.getUserByName(localStorage.getItem('currentUserName')).subscribe(
      (user: User) => {

        this.user = user;
        console.log(this.user);
        this.newPhoto.user = this.user;

        this.addPhotoService.sendPhoto(this.newPhoto)
          .subscribe(
            (_data) => {
              this.photoAdded = true;
              this.newPhoto = new Photo();
            },
            (error) => console.log(error)
          );
      },
      (error) => console.log(error)
    );
  }

}
