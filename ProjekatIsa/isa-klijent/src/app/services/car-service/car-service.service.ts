import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Car } from '../../models/Car';
import { AuthServiceService } from '../auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {

  constructor(private http: HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    addCar(car : Car) : Observable<any> {
      return this.http.post('http://localhost:8080/car/addCar',car,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
    
    deleteCar(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/car/deleteCar',id,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    getCars(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/car/getCars',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    getAll(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/car/getAll',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }

    
    changeCar(newCar: Car, id: number) : Observable<any> {
        return this.http.post('http://localhost:8080/car/changeCar/'+id,newCar,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
    
    searchCars(startDate : Date, endDate : Date,id : number,category : string,cenaOd : number,cenaDo:number) :Observable<any>{
        console.log('usao u pretraga servis');
        return this.http.get('//localhost:8080/car/searchCar/'+startDate+"/"+endDate+"/"+id+"/"+category+"/"+cenaOd+"/"+cenaDo);
  }
    
     getDiscountCars(id:number): Observable<any>{
  
        return this.http.get('http://localhost:8080/car/getDiscountCars/'+id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
     getCarById(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/car/getCarById',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    
    
    
    
    
}
