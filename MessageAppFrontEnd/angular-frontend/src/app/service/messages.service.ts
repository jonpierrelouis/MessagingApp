import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UserIdService } from './user-id.service';
import Message from '../models/Message';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http: HttpClient, private id: UserIdService) { }

  private baseLoginUrl = `${environment.baseUrl}/messages`;

  getMessages(friendId: number){
    
    return this.http.get<Message[]>(`${this.baseLoginUrl}/getMessages/${this.id.getUserId()}/${friendId}`);
  }

  sendMessage(friendId: number, messageBody: string){

    const newMessage = {
      "messageId" : 0, 
      "senderId" : this.id.getUserId(),
      "recipientId" : friendId,
      "messageBody": messageBody
    }

    return this.http.post<Message[]>(`${this.baseLoginUrl}/sendMessage/`, JSON.stringify(newMessage), {headers: environment.jsonHeaders, withCredentials: environment.withCredentials});
  }
}
