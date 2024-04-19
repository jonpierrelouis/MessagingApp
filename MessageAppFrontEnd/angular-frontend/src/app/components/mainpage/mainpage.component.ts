import { Component, OnInit } from '@angular/core';
import Friend from 'src/app/models/Friend';
import { FriendsService } from 'src/app/service/friends.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  friendsList: Friend[];
  resp: any;

  constructor(private friendService: FriendsService) {

    this.friendService.getFriends().subscribe( resp => {
      this.resp = resp;
      console.log(resp);
    })

    this.friendsList = this.resp;
   }

  ngOnInit(): void {

   }
  
}
