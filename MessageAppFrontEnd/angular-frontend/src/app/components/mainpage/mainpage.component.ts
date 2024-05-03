import { Component, OnInit } from '@angular/core';
import Message from 'src/app/models/Message';
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

  messageArray: BehaviorSubject<Message[]> = new BehaviorSubject<Message[]>([]);

  ngOnInit(): void {
    this.showMessages();
   }

   ngOnChanges(): void {
    this.showMessages();
   }

   showMessages(){
    this.friendIdService.getFriendId().subscribe( friendId => {
      this.getMessages(friendId);
    })
   }

   getMessages(friendId: number){
    this.messageService.getMessages(friendId).subscribe( resp => {
      this.messageArray.next(resp);
      console.log(resp);
    })
   }
  
}
