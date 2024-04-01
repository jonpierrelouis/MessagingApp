import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private baseLoginUrl = `${environment.baseUrl}/user`;

  constructor(private http : HttpClient) { }

  loginUser(username: string, password: string){

    let params = new HttpParams()
      .set('email', username)
      .set('password', password);

    return this.http.post(`${this.baseLoginUrl}/login`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials} );

  }
}
