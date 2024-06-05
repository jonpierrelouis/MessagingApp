
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import MessageDTO from '../models/MessageDTO';
import { MessagesService } from './messages.service';

@Injectable({
  providedIn: 'root'
})
export class FriendIdService {

  private friendId = new BehaviorSubject<number>(0);

  private messages = new BehaviorSubject<MessageDTO[]>([]);

  constructor(private messageService: MessagesService) { }

  setFriendId(friendId: number){
    this.friendId.next(friendId);
    this.pullMessages(friendId);
  }

  getFriendId(): Observable<number>{
    return this.friendId;
  }

  private pullMessages(friendId: number){

    this.messageService.getMessagesDTO(friendId).subscribe( resp => {
      this.messages.next(resp);
      console.log(resp);
    })
  }

  getMessages(): Observable<MessageDTO[]>{
    return this.messages;
  }
}
