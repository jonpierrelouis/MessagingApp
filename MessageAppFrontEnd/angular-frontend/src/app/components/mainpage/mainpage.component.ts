import { Component, OnInit } from '@angular/core';
import MessageDTO from 'src/app/models/MessageDTO';
import { FriendIdService } from 'src/app/service/friend-id.service';
import { MessagesService } from 'src/app/service/messages.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  constructor(private messageService: MessagesService, private friendIdService: FriendIdService) { }

  messageArray: BehaviorSubject<MessageDTO[]> = new BehaviorSubject<MessageDTO[]>([]);

  newMessage: string = "";
  friendId: number = 0;

  ngOnInit(): void {
    this.friendIdService.getMessages().subscribe(messages => {
      this.messageArray.next(messages);
    })
   }

  sendMessage(){

    console.log("message " ,this.newMessage)

    //get friend id
    this.friendIdService.getFriendId().subscribe(friendId => {
      this.friendId = friendId;
    })

    //send message
    this.messageService.sendMessage(this.friendId , this.newMessage).subscribe(messages => {
      this.messageArray.next(this.messageArray.getValue().concat(messages));
    })

    this.newMessage = "";
  }

  
}
