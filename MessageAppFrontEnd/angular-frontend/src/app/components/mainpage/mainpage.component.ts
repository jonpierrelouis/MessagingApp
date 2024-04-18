import { Component, OnInit, AfterViewInit } from '@angular/core';
import Friend from 'src/app/models/Friend';
import { FriendsService } from 'src/app/service/friends.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  friends: Friend[] = [];

  constructor(private friendService: FriendsService) { }

  ngOnInit(): void { }
  
  ngAfterViewInit():void {
    this.friendService.getFriends().subscribe( friendList => {
      this.friends.concat(friendList);
      console.log(friendList);
    })
  }
}
