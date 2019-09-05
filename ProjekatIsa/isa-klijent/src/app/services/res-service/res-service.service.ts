import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CarReservation } from '../../models/CarReservation';
import { AuthServiceService } from '../auth-service.service';
@Injectable({
  providedIn: 'root'
})
export class ResServiceService {

  constructor(private http : HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    reserveCar(car : CarReservation) : Observable<any> {
      return this.http.post('http://localhost:8080/carReservation/reserveCar',car,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
}
