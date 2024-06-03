
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FriendIdService {

  private friendId = new BehaviorSubject<number>(0);

  constructor() { }

  setFriendId(friendId: number){
    this.friendId.next(friendId);
    console.log(this.friendId);
  }

  getFriendId(): Observable<number>{
    return this.friendId;
  }
}
