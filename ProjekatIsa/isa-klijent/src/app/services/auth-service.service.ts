import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class AuthServiceService {
  private token: string;
  constructor(private http: HttpClient) { this.token = "jwtToken"; }
  
  //private user:/*{ id: number }*/any | null;
  //roles: string[] = [] ;
    
    
  getJwtToken() {
    return localStorage.getItem(this.token);
  };

   setJwtToken(token) {
      localStorage.setItem(this.token, token);
  };

  removeJwtToken() {
      localStorage.removeItem(this.token);
  };

   createAuthorizationTokenHeader() {
      var token = this.getJwtToken();
      if (token) {
          return {
            "Authorization": "Bearer " + token,
            'Content-Type': 'application/json'
          };
      } else {
          return {
            'Content-Type': 'application/json'
          };
      }
  }

  /*public logoutUser(){
    this.token=null;
    this.user=null;
    localStorage.clear();
  }
  public getUser():/* { id: number }any | null {
    //return this.user || JSON.parse(localStorage.getItem('user'));
  /*}

  public getToken(): string {
    return this.token || localStorage.getItem('token') || '';
  }

  public setUser(user : any): void {
    this.user = user;
    localStorage.setItem('user', JSON.stringify(user));
  }

  public setToken(token: string): void {
    this.token = token;
    localStorage.setItem('token', token);
  }
  public getRole(): string {
    this.roles = this.tokenStorage.getAuthorities();
    let userRole = '';
      for (const role of this.roles) {
        if (role === 'SYSTEM_ADMIN') {
          userRole = 'SYSTEM_ADMIN';
        } else if (role === 'USER') {
          userRole = 'USER';
        } else if (role === 'AVIO_ADMIN') {
          userRole = 'AVIO_ADMIN';
        } else if (role === 'HOTEL_ADMIN') {
          userRole = 'HOTEL_ADMIN';
        } else {
          userRole = 'CAR_ADMIN';
        }
      }
    return userRole;
  }*/
}
