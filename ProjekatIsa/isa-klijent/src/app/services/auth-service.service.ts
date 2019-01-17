import { Injectable } from '@angular/core';

@Injectable()
export class AuthServiceService {

  constructor() { }
  private token: string;
  private user:/*{ id: number }*/any | null;

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
}
