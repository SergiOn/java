import { TestBed } from '@angular/core/testing';

import { GetBookListService } from 'src/app/services/get-book-list.service';

describe('GetBookListServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetBookListService = TestBed.get(GetBookListService);
    expect(service).toBeTruthy();
  });
});
