import { Component, Input, OnInit} from '@angular/core';
import { FriendsService } from 'src/app/service/friends.service';
import { UserIdService } from 'src/app/service/user-id.service';

@Component({
  selector: 'app-friends-bar',
  templateUrl: './friends-bar.component.html',
  styleUrls: ['./friends-bar.component.css']
})
export class FriendsBarComponent implements OnInit {

  @Input() friendsList: any;
  resp: any;

  addFriendBox: boolean = false;
  newFriend: string = "";

  constructor(private friendService: FriendsService, private userIdService: UserIdService) { }

  ngOnInit(): void {

    this.friendService.getFriends().subscribe( resp => {
      this.resp = resp;
      console.log(resp);
    })

  }

  ngOnChanges(): void {
    console.log("friends bar")
    console.log(this.friendsList)
  }

  openAddFriendBox(){
    this.addFriendBox = !this.addFriendBox;
  }

  sendAddFriend(){
    this.friendService.addFriend(this.userIdService.getUserId(), this.newFriend).subscribe( resp => {
      console.log(resp);
    })
  }
  showMessages(){
    
  }

}
