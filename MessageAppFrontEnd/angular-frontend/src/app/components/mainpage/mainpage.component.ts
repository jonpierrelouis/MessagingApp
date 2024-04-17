import { Component, OnInit } from '@angular/core';
import Friend from 'src/app/models/Friend';
import { FriendsService } from 'src/app/service/friends.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  friend = this.friendService.getFriends();

  constructor(private friendService: FriendsService) { }

  ngOnInit(): void {
    console.log(this.friend)
  }

}
