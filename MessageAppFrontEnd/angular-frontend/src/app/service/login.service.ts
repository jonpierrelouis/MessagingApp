import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private baseLoginUrl = `${environment.baseUrl}`;

  constructor(private http : HttpClient) { }

  /**
   * This method logins user given username and password
   * @param username 
   * @param password 
   * @returns login
   */
  loginUser(username: string, password: string){

    let params = new HttpParams()
      .set('username', username)
      .set('password', password);

    return this.http.post(`${this.baseLoginUrl}/login`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials} );

  }
}
