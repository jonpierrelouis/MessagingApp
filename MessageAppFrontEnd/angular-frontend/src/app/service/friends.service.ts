import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import Friend from '../models/Friend';
import { UserIdService } from './user-id.service';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  private baseLoginUrl = `${environment.baseUrl}/friends`;

  constructor(private http: HttpClient, private id: UserIdService) { }

  getFriends(){

    return this.http.get<Friend[]>(`${this.baseLoginUrl}/getFriends/${this.id.getUserId()}`);
  }

  addFriend(userId: number, newFriend: string){

    let friend = {
      "userId": userId,
      "newFriend": newFriend
    }

    return this.http.post<Friend>(`${this.baseLoginUrl}/addFriend`, JSON.stringify(friend) , {headers: environment.jsonHeaders, withCredentials: environment.withCredentials})
  }

}
