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
        return this.http.post('http://localhost:8080/api/mainSecurity/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    
    logOut() {
        window.sessionStorage.clear();
        return this.http.get('http://localhost:8080/auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
      }
    
    registerUser(u: User) {

        console.log('Usao u addUser');
        console.log(u.email,u.password,u.firstName,u.lastName);

        return this.http.post('http://localhost:8080/api/registerUser', u );
      }
    registerAdmin(u: User, num : number) {

        console.log('Usao u registerAdmin');

        return this.http.post('http://localhost:8080/api/registerAdmin/'+num, u );
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


  updateUser(user) {
    return this.http.put('http://localhost:8080/api', user);

  }
}
   

