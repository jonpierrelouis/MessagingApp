/**
 * Used to connect the mainpage component to the friend component. They share a parent to grandchild relationship,
 * so data could technically be shared from parent to child to grandchild, but this would be a little complex and 
 * introduce tightly coupled code
 */
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
  }

  getFriendId(): Observable<number>{
    return this.friendId;
  }
}
