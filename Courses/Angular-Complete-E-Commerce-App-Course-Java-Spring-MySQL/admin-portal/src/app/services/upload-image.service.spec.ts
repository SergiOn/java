import { TestBed } from '@angular/core/testing';

import { UploadImageService } from './upload-image.service';

describe('UploadImageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UploadImageService = TestBed.get(UploadImageService);
    expect(service).toBeTruthy();
  });
});
