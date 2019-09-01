import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers } from "@angular/http";

import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import 'rxjs/Rx'


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams  } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AviocompanySService {
       

    
    private a = new BehaviorSubject<any>(null);
  currentCompany = this.a.asObservable();


  constructor(private http: HttpClient) { 

  }

  getAvioCompany(){

   
       return this.http.get('http://localhost:8080/avioCompany/getAll')//.map(res=>res.json());
   /* const headers = new Headers();
    headers.append('Content-Type', 'application/json');
   
      
   return this.http.get("https://jsonplaceholder.typicode.com/users", {headers:headers}).map(data => data.json())
  
    .catch((err:HttpErrorResponse) =>
    {
        // alert(err.status + " " + err.error.error + " \n" + err.error.message);
        return Observable.throw(err);
    });*/
 
      }

     selectAviocompany(company : any) {

    this.a.next(company);
  }

  addAviocompany(aviocompany) {
    return this.http.post('http://localhost:8080/avioCompany', aviocompany);

  }
  deleteAviocompany(id: string) {
    return this.http.delete('http://localhost:8080/avioCompany/'.concat(id));
  }

  updateAviocompany(aviocompany) {
    return this.http.put('http://localhost:8080/avioCompany/update', aviocompany);

  }
}
