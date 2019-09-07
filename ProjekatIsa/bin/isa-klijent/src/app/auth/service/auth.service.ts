import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthLoginInfo } from '../forms/login-info';
import { JwtResponse } from '../response/jwt-response';
import { SignUpInfo } from '../forms/register-info';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
        
    private loginUrl = 'http://localhost:8080/api/auth/signin';
    private signupUrl = 'http://localhost:8080/api/auth/signup';


  constructor(private http: HttpClient) { }
    
    //MILAN: prosirio sam metodu kako bih prosledio parametre sa login forme na server
    attemptAuth(email:string,password:string): Observable<JwtResponse> {
    const loginform = {"email":email, "password":password};
    return this.http.post<JwtResponse>(this.loginUrl, loginform, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
