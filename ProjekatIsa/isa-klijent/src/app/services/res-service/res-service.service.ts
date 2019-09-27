import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CarReservation } from '../../models/CarReservation';
import { AuthServiceService } from '../auth-service.service';
import { FlightReservation } from '../../models/FlightReservation';

@Injectable({
  providedIn: 'root'
})
export class ResServiceService {

  constructor(private http : HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    reserveCar(car : CarReservation) : Observable<any> {
        return this.http.post('http://localhost:8080/carReservation/reserveCar',car,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
    
    getUserRes(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/carReservation/getUserRes/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    cancelReservation(id : number){
        return this.http.delete('http://localhost:8080/carReservation/delete/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  }
    
    getUserHotelRes(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/carReservation/getUserHotelRes/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    getUserFlightRes(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/carReservation/getUserFlightRes/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    cancelRoomReservation(id : number){
        return this.http.delete('http://localhost:8080/carReservation/deleteHotelR/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  }
    
    getFlight(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/carReservation/getFlight/'+5,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    
    cancelFlightReservation(id : number){
        return this.http.delete('http://localhost:8080/carReservation/deleteFlight/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  }
    
    fastReservations(idFlight: any, idCar: any ,  startDate: Date, endDate: Date,idUser): Observable<any> {
        return this.http.get('http://localhost:8080/carReservation/fastReservations/'+idFlight+ "/" + idCar + "/" + startDate +"/" +endDate+"/"+idUser,{headers: this.auth.createAuthorizationTokenHeader()});
  }
    fastReservationsHotel(idFlight: any, idRoom: any ,  startDate: Date, endDate: Date,idUser :number,idRes:number): Observable<any> {
        return this.http.get('http://localhost:8080/reservationRoom/fastReservationsHotel/'+idFlight+ "/" + idRoom + "/" + startDate +"/" +endDate+"/"+idUser+"/"+idRes);
    };
    
}
