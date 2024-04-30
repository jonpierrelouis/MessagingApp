import { TestBed } from '@angular/core/testing';

import { FriendIdService } from './friend-id.service';

describe('FriendIdService', () => {
  let service: FriendIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FriendIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
