import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import Login from '../models/Login';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private baseLoginUrl = `${environment.baseUrl}/user`;

  constructor(private http : HttpClient) { }

  /**
   * This method logins user given username and password
   * @param username 
   * @param password 
   * @returns login
   */
  loginUser(username: string, password: string): Observable<Login>{

    let params = new HttpParams()
      .set('username', username)
      .set('password', password);

    return this.http.post<Login>(`${this.baseLoginUrl}/login`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials} );
  }

  registerUser(username: string, password: string): Observable<Login>{

    let params = new HttpParams()
      .set('username', username)
      .set('password', password);

    return this.http.post<Login>(`${this.baseLoginUrl}/register`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials})
  }
}
