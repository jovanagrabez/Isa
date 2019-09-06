import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from './auth-service.service';
import { RentACar } from '../models/RentACar';
import { Filijale } from '../models/Filijale';




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
  
  getFilijale(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/rentalcars/getFilijale',id,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
  changeService(newService: RentACar, id: number) : Observable<any> {
        return this.http.post('http://localhost:8080/rentalcars/changeService/'+id,newService,{headers: this.auth.createAuthorizationTokenHeader()}); 
  }
  
  deleteService(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/rentalcars/deleteService',id,{headers: this.auth.createAuthorizationTokenHeader()}); 

  }
    
  addFil(newFil: Filijale, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/rentalcars/addFil/'+id,newFil,{headers: this.auth.createAuthorizationTokenHeader()}); 

  }
    
    selectRentACar(rentalcars : any) {
    this.h.next(rentalcars);
  }
    
    
    
  
}
