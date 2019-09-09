import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import {Http, Response, Headers } from "@angular/http";

import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { SortForm } from '../models/SortForm';
import { AvioCompany } from '../models/AvioCompany';

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

  getCompany(id) {
    return this.http.get('http://localhost:8080/avioCompany/'.concat(id));
  }

  getCompanyByFlight(id) {
    return this.http.get('http://localhost:8080/avioCompany/flight/'.concat(id));
  }
    
  sortingService(sortForm: SortForm, servisi: Array<AvioCompany>) {
    var item = sortForm.sortItem;
    console.log(item);
    var type = sortForm.sortType;
    console.log(type);
    console.log(servisi.length);
    console.log('sorting rent a car');
    var sending= item + '=' + type;
    console.log(item);
    console.log(type);
    // tslint:disable-next-line:max-line-length
    return this.http.post('http://localhost:8080/avioCompany/sortForm/' + sending, servisi);
  }

  getLastWeekReservations(id: any, dateToday: string) {
    return this.http.get('http://localhost:8080/avioCompany/getLastWeekReservations/' + id + '/' + dateToday);

  }

  getAllReservations(id: any) {
    return this.http.get('http://localhost:8080/avioCompany/getAllReservations/' + id);


  }

  getAvioRevenue(id: any, od: string, doo: string) {
    return this.http.get('http://localhost:8080/avioCompany/getHotelRevenue/' + id  + '/' + od + '/' + doo);
  }

  getAllRatingsCompany(id: any) {
    return this.http.get('http://localhost:8080/avioCompany/getAllRatingsHotel/' + id );

  }

  getRatingFlight(id: any) {
    return this.http.get('http://localhost:8080/avioCompany/getRatingRoom/' + id);

  }
}
