import { Injectable } from '@angular/core';
import {TokensService} from '../auth/tokens/tokens.service';

@Injectable()
export class AuthServiceService {

  constructor( private tokenStorage: TokensService) { }
  private token: string;
  private user:/*{ id: number }*/any | null;
  roles: string[] = [] ;

  public logoutUser(){
    this.token=null;
    this.user=null;
    localStorage.clear();
  }
  public getUser():/* { id: number }*/any | null {
    return this.user || JSON.parse(localStorage.getItem('user'));
  }

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
  }
}
