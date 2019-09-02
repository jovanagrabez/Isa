import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from './auth-service.service';


import 'rxjs/Rx'


import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

@Injectable({
  providedIn: 'root'
})
export class ViewRentalCarsService {


  private h = new BehaviorSubject<any>(null);
  currentRentACar = this.h.asObservable();

  constructor(private http: HttpClient, private auth : AuthServiceService) { }
  
  getRentalCars(): Observable<any>{
  
        return this.http.get('http://localhost:8080/rentalcars/getAll');
  }
    
  getCars(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/rentalcars/getCars',id,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    selectRentACar(rentalcars : any) {
    this.h.next(rentalcars);
  }
    
  
}
