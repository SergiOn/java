import { Component } from '@angular/core';
import { PhotoService } from '../../services/photo/photo.service';
import { Photo } from '../../models/photo';

@Component({
  selector: 'photo-row',
  templateUrl: './photo-row.component.html',
  styleUrls: ['./photo-row.component.css']
})
export class PhotoRowComponent {

  photoList: Photo[];
  photoListSorted: Photo[];
  photoListRanked: Photo[];

  constructor (private photoService: PhotoService) {
    this.photoService.getPhotos().subscribe(
      (data: Photo[]) => {
        this.photoList = data;
        this.photoListSorted = this.photoList.sort((a, b) => b.likes - a.likes);
        this.photoListRanked = this.photoListSorted.slice(0, 3);
      },
      error => console.log(error)
    );
  }

}
