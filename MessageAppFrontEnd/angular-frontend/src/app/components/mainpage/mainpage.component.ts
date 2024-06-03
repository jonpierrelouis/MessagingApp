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

  ngOnInit(): void { }

  //  ngOnChanges(): void {
  //   console.log("hello")
  //   this.changeFriend();

  //  }

   changeFriend(){
    this.friendIdService.getFriendId().subscribe( friendId => {
      this.getMessages(friendId);
    })
   }

   getMessages(friendId: number){
    this.messageService.getMessagesDTO(friendId).subscribe( resp => {
      this.messageArray.next(resp);
      console.log(resp);
    })
   }
  
}
