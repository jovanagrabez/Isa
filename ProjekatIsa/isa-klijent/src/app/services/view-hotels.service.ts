import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers } from "@angular/http";
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { SearchFormHotel } from '../models/SearchFormHotel';
import { SortForm } from '../models/SortForm';
import { Hotel } from '../models/Hotel';

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
  
  getHotels(): Observable<any>{
	    return this.http.get('http://localhost:8080/hotels/getAll')
  };
  
  getAllRooms(id:number): Observable<any>{    
      return this.http.get('http://localhost:8080/rooms/getAllRooms/'+id)
  };  
  getAllServices(id:number): Observable<any>{
      
      return this.http.get('http://localhost:8080/addServices/getAllServices/'+id)

}
  
   selectHotel(hotel : any) {

    this.h.next(hotel);
  }
  
  getRooms(){
  	
  	    return this.http.get('http://localhost:8080/rooms/getAll')
  
  }
  searchHotels(searchForm: SearchFormHotel) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/searchHotels/',searchForm); 
  };
    
  sortingHotels(sortForm: SortForm, hoteli: Array<Hotel>) {
    var item = sortForm.sortItem;
    console.log(item);
    var type = sortForm.sortType;
    console.log(type);
    console.log(hoteli.length);
    console.log('sorting rent a car');
    var sending= item + '=' + type;
    console.log(item);
    console.log(type);
    // tslint:disable-next-line:max-line-length
    return this.http.post("http://localhost:8080/hotels/sortForm/" + sending, hoteli);
  }
}
