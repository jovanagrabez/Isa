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
export class ViewRentalCarsService {


  private h = new BehaviorSubject<any>(null);
  currentRentACar = this.h.asObservable();

  constructor(private http: HttpClient) { }
  
  getRentalCars(){
  
        return this.http.get('http://localhost:8080/rentalcars/getAll')
    
    
    /*const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http.get("http://localhost:8080/hotels/getAll", {headers:headers}).map(data => data.json())
    //return this.http.get('https://jsonplaceholder.typicode.com/users')
    
    .catch((err:HttpErrorResponse) =>
    {
    // alert(err.status + " " + err.error.error + " \n" + err.error.message);
    return Observable.throw(err);
    });
  */
  }
    
    selectRentACar(rentalcars : any) {

    this.h.next(rentalcars);
  }
}
