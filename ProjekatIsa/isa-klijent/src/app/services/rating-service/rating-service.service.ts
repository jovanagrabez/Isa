import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CarReservation } from '../../models/CarReservation';
import { AuthServiceService } from '../auth-service.service';
import { RatingCar } from '../../models/RatingCar';
import { RatingRoom } from '../../models/RatingRoom';
import { RatingService } from '../../models/RatingService';
import { RatingHotel } from '../../models/RatingHotel';


@Injectable({
  providedIn: 'root'
})
export class RatingServiceService {

  constructor(private http : HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    rateCar(rate : RatingCar): Observable<any>{
        console.log(rate.rate);
        return this.http.post('http://localhost:8080/rating/rateCar',rate,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    rateRoom(rate : RatingRoom): Observable<any>{
        console.log(rate.rate);
        return this.http.post('http://localhost:8080/rating/rateRoom',rate,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    getUserRatings(id : number):Observable<any>{

        return this.http.get('http://localhost:8080/rating/userCarRating/'+id,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
     getUserRoomRes(id : number):Observable<any>{

        return this.http.get('http://localhost:8080/rating/userRoomRating/'+id,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    rateService(rate : RatingService,id:number): Observable<any>{
        console.log(rate.rate);
        return this.http.post('http://localhost:8080/rating/rateService/'+id,rate,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    getServiceRatings(id : number):Observable<any>{

        return this.http.get('http://localhost:8080/rating/userServiceRating/'+id,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    rateHotel(rate : RatingHotel,id:number): Observable<any>{
        console.log(rate.rate);
        return this.http.post('http://localhost:8080/rating/rateHotel/'+id,rate,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    
    getHotelRatings(id : number):Observable<any>{

        return this.http.get('http://localhost:8080/rating/userHotelRating/'+id,{headers: this.auth.createAuthorizationTokenHeader()});
  }
}
