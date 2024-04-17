import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import Friend from '../models/Friend';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FriendsService {

  private baseLoginUrl = `${environment.baseUrl}`;

  constructor(private http: HttpClient) { }

  getFriends(){
    return this.http.get<Friend[]>(`${this.baseLoginUrl}/getFriends`);
  }

  addFriend(userId: number){

    let params = new HttpParams()
      .set('userId', userId);

    return this.http.post<Friend>(`${this.baseLoginUrl}/addFriend`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials})
  }

}
