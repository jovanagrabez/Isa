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
  };
}
