import { Component, OnInit } from '@angular/core';
import Friend from 'src/app/models/Friend';
import Message from 'src/app/models/Message';
import { FriendIdService } from 'src/app/service/friend-id.service';
import { FriendsService } from 'src/app/service/friends.service';
import { MessagesService } from 'src/app/service/messages.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  constructor(private messageService: MessagesService, private friendIdService: FriendIdService) { }

  messageArray: Message[] = [];

  ngOnInit(): void {

    this.friendIdService.getFriendId().subscribe( friendId => {
      this.getMessages(friendId);
    })

   }

   getMessages(friendId: number){
    this.messageService.getMessages(friendId).subscribe( resp => {
      this.messageArray = resp;
    })
   }
  
}
