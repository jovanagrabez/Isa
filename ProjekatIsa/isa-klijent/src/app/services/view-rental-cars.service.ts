import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from './auth-service.service';
import { RentACar } from '../models/RentACar';
import { Filijale } from '../models/Filijale';
import { SearchFormServices } from '../models/SearchFormServices';
import { SortForm } from '../models/SortForm';





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
  
  addService(newService: RentACar): Observable<any>{
      return this.http.post('http://localhost:8080/rentalcars/addService',newService,{headers: this.auth.createAuthorizationTokenHeader()}); 

  }
    
  searchService(searchForm: SearchFormServices) : Observable<any> {
      return this.http.post('http://localhost:8080/rentalcars/searchService/',searchForm); 
  }
    
    
  sortingService(sortForm: SortForm, servisi: Array<RentACar>) {
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
    return this.http.post("http://localhost:8080/rentalcars/sortForm/" + sending, servisi,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    selectRentACar(rentalcars : any) {
    this.h.next(rentalcars);
  }
    
  getAllCars(id : number): Observable<any>{
          return this.http.get('http://localhost:8080/rentalcars/getAllCars/'+id);
  }
    
    
  getLastWeekReservations(id: number, dateToday : string): Observable<any> {
      return this.http.get('http://localhost:8080/rentalcars/getLastWeekReservations/'+id+"/"+dateToday,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  
  getAllReservations(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/rentalcars/getAllReservations/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
    
  getServiceRevenue(id: number, od :string, Do : string) : Observable<any> {
      return this.http.get('http://localhost:8080/rentalcars/getServiceRevenue/'+id +'/' + od +'/' + Do,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
    
  getRatingCar(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/car/getRatingCar/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
    
  getAllRatingsService(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/rentalcars/getAllRatingsService/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };


  searchServiceFast(searchForm: SearchFormServices,id:number): Observable<any> {
    return this.http.post('http://localhost:8080/rentalcars/searchFast/'+id, searchForm);


  }
}
