import { Component, OnInit, Input } from '@angular/core';
import Friend from 'src/app/models/Friend';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  colored: boolean = true;

  @Input() friend: Friend

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    console.log(this.friend)
  }


  /**
   * When component is clicked, this will show message history on the mainpage
   */
  ActivateMessage(){
    
  }


}
