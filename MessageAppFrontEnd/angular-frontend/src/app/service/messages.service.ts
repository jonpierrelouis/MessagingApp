import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UserIdService } from './user-id.service';
import { Observable } from 'rxjs';
import MessageDTO from '../models/MessageDTO';
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

  getMessagesDTO(friendId: number): Observable<MessageDTO[]>{

    return this.http.get<MessageDTO[]>(`${this.baseLoginUrl}/getMessagesDTO/${this.id.getUserId()}/${friendId}`);
  }

  sendMessage(friendId: number, messageBody: string): Observable<MessageDTO[]> {

    const newMessage = {
      "messageId" : 0, 
      "senderId" : this.id.getUserId(),
      "recipientId" : friendId,
      "messageBody": messageBody
    }

    return this.http.post<MessageDTO[]>(`${this.baseLoginUrl}/sendMessageBody/`, JSON.stringify(newMessage), {headers: environment.jsonHeaders, withCredentials: environment.withCredentials});
  }
}
