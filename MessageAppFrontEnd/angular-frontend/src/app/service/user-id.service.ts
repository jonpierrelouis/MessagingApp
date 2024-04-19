import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserIdService {

  userId: number = 0;

  constructor() { }

  setUserId(newUserId: number){
    this.userId = newUserId;
  }

  getUserId(): number{
    return this.userId;
  }
}
