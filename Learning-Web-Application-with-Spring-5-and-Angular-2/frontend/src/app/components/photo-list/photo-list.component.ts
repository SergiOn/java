import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../../services/photo.service';
import { User } from '../../models/user';
import { Photo } from '../../models/photo';

@Component({
  selector: 'photo-list',
  templateUrl: './photo-list.component.html',
  styleUrls: ['./photo-list.component.css']
})
export class PhotoListComponent implements OnInit {
  photos: Photo[];
  selectedPhoto: Photo;

  constructor (private photoService: PhotoService) {
    this.photoService.getPhotos().subscribe(
      (data: Photo[]) => console.log(this.photos = data),
      (error) => console.log(error)
    );
  }

  ngOnInit() {
  }

}
