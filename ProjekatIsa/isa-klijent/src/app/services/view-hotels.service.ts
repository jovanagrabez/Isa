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
export class ViewHotelsService {


  private h = new BehaviorSubject<any>(null);
  currentHotel = this.h.asObservable();

  constructor(private http: HttpClient) { }
  
  getHotels(){
	    return this.http.get('http://localhost:8080/hotels/getAll')
  };
  
  getAllRooms(id:number): Observable<any>{    
      return this.http.get('http://localhost:8080/hotels/getAllRooms/'+id)
  };  
  getAllServices(id:number): Observable<any>{
      
      return this.http.get('http://localhost:8080/hotels/getAllServices/'+id)

}
  
   selectHotel(hotel : any) {

    this.h.next(hotel);
  }
  
  getRooms(){
  	
  	    return this.http.get('http://localhost:8080/rooms/getAll')
  
  }
}
