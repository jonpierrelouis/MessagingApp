import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-friends-bar',
  templateUrl: './friends-bar.component.html',
  styleUrls: ['./friends-bar.component.css']
})
export class FriendsBarComponent implements OnInit {

  @Input() friendsList: any;
  List = [0,1,2,3];

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    console.log("friends bar")
    console.log(this.friendsList)
  }

  showMessages(){
    console.log("what")
  }

}
