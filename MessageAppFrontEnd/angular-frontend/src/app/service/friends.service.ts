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

  addFriend(userId: number){

    let params = new HttpParams()
      .set('userId', userId);

    return this.http.post<Friend>(`${this.baseLoginUrl}/addFriend`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials})
  }

}
