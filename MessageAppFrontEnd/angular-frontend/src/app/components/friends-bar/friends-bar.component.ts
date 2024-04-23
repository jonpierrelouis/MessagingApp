import { Component, Input, OnInit } from '@angular/core';
import { FriendsService } from 'src/app/service/friends.service';

@Component({
  selector: 'app-friends-bar',
  templateUrl: './friends-bar.component.html',
  styleUrls: ['./friends-bar.component.css']
})
export class FriendsBarComponent implements OnInit {

  @Input() friendsList: any;
  resp: any;
  List = [0,1,2,3];

  constructor(private friendService: FriendsService) { }

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

  showMessages(){
    console.log("what")
  }

}
