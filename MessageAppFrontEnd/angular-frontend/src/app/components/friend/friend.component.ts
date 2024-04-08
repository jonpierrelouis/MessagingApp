import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  colored: boolean = true;

  // @Output() showMessagesEvent = new EventEmitter<number>();
  @Input() friendInput: any;

  constructor() { }

  ngOnInit(): void {
  }

  isActive(){
    console.log("hello from ", this.friendInput);
  }


}
