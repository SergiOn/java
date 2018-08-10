import { TestBed, inject } from '@angular/core/testing';

import { AddPhotoService } from './add-photo.service';

describe('AddPhotoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddPhotoService]
    });
  });

  it('should be created', inject([AddPhotoService], (service: AddPhotoService) => {
    expect(service).toBeTruthy();
  }));
});
