import { Component, OnInit, Input } from '@angular/core';
import Friend from 'src/app/models/Friend';
import { FriendIdService } from 'src/app/service/friend-id.service';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  colored: boolean = true;

  @Input() friend: Friend

  constructor(private friendId: FriendIdService) { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    console.log(this.friend)
  }


  /**
   * When component is clicked, this will show message history on the mainpage by sending 
   * a new friend id with the friendId service
   */
  ActivateMessage(){
    console.log(this.friend.userId);
    this.friendId.setFriendId(this.friend.userId);
  }


}
