import { TestBed } from '@angular/core/testing';

import { LeaveReqService } from './leave-req.service';

describe('LeaveReqService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LeaveReqService = TestBed.get(LeaveReqService);
    expect(service).toBeTruthy();
  });
});
