import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable()
export class AuthServiceService {
  private token: string;
  private roles: Array<string> = [];

  
  constructor(private http: HttpClient) { this.token = "jwtToken"; }
    
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
  
 public saveAuthorities(authorities: string[]) {
    window.localStorage.removeItem(AUTHORITIES_KEY);
    window.localStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (localStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
    }

    return this.roles;
  }

  
}
