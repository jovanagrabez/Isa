import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

let url : string = "//localhost:8080/api/users/login";

@Injectable()
export class LoginService {
 

  constructor(private http: HttpClient) { }
  
  login(user: Object): Observable<any> {
    return this.http.post('//localhost:8080/api/auth/signin',user)
  } 
            
      
  register(user:Object) :Observable<any> {
    return this.http.post("//localhost:8080/api/auth/signup",user);
  }

  sendMail(id) {
    return this.http.get("//localhost:8080/api/users/send/"+id);
  }

  verify(id):Observable<any> {
    return this.http.get("//localhost:8080/api/users/verify/"+id);
  }
}
