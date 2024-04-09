import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-friends-bar',
  templateUrl: './friends-bar.component.html',
  styleUrls: ['./friends-bar.component.css']
})
export class FriendsBarComponent implements OnInit {

  @Input() item = '';

  arr = [0,1,2,3,4,5,6,7,8,9,10];

  constructor() { }

  ngOnInit(): void {
  }

  showMessages(){
    console.log("what")
  }

}
