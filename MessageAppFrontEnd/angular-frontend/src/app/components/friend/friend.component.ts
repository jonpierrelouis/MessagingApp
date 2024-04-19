import { Component, OnInit, Input } from '@angular/core';
import Friend from 'src/app/models/Friend';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  colored: boolean = true;

  @Input() friend: string = "";
  @Input() friendId: any = 0;

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    console.log("from friend")
    console.log(this.friend)
  }


  isActive(){
    console.log("hello from ", this.friend);
  }


}
