import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PhotoService } from '../../services/photo/photo.service';
import { User } from '../../models/user';
import { Photo } from '../../models/photo';

@Component({
  selector: 'photo-list',
  templateUrl: './photo-list.component.html',
  styleUrls: ['./photo-list.component.css']
})
export class PhotoListComponent {
  photos: Photo[];
  selectedPhoto: Photo;

  constructor (private router: Router, private photoService: PhotoService) {
    this.photoService.getPhotos().subscribe(
      (data: Photo[]) => console.log(this.photos = data),
      (error) => console.log(error)
    );
  }

  onSelect(photo: Photo) {
    this.selectedPhoto = photo;
    this.router.navigate(['/image-detail', this.selectedPhoto.photoId]);
  }

}
