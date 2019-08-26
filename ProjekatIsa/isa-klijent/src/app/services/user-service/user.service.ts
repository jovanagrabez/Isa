import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Http, Response, Headers } from "@angular/http";
import { HttpHeaders,HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { AuthServiceService } from '../auth-service.service';
import { User } from '../../models/user';



import 'rxjs/Rx'


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private router : Router,private auth : AuthServiceService) { }
  
    
  loginUser(u: User) {
        console.log('Usao u loginUser');
            let user={
                 "email": u.email,
                 "password": u.password
                     };
        console.log(user.email,user.password);
        return this.http.post('http://localhost:8080/auth/login', user, {headers: this.auth.createAuthorizationTokenHeader()});
        }
    
  getLogged(token: string) {
        console.log("token: " + token);
        return this.http.post('http://localhost:8080/api/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    
    logOut() {
        window.sessionStorage.clear();
        return this.http.get('http://localhost:8080/auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
      }
    
   /* getUsers(){
    
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    //return this.http.get("http://localhost:8080/users/getAll", {headers:headers}).map(data => data.json())

    //.catch((err:HttpErrorResponse) =>
    {
        // alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
   // });
      }*/
    
   

  
  }
   

