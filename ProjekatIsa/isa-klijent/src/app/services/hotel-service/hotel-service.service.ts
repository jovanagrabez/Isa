import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Router} from '@angular/router';
import { Observable } from 'rxjs';
import { Hotel } from '../../models/Hotel';
import { Room } from '../../models/Room';
import { ReservationRoom } from '../../models/ReservationRoom';

import {AuthServiceService} from '../auth-service.service';
import { AdditionalServiceForHotel } from '../../models/AdditionalServiceForHotel';



@Injectable({
  providedIn: 'root'
})
export class HotelServiceService {

  constructor(private http: HttpClient, private router : Router,private auth: AuthServiceService) { }

  getAllAdditionalServices() : Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getAllAdditionalServices',{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  
  addHotel(hotel : Hotel) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/addHotel',hotel,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  
  changeHotel(newHotel: Hotel, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/changeHotel/'+id,newHotel,{headers: this.auth.createAuthorizationTokenHeader()}); 
  }; 
  
  deleteHotel(id:number) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/deleteHotel',id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  
  addService(newService: AdditionalServiceForHotel, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/hotels/addService/'+id,newService,{headers: this.auth.createAuthorizationTokenHeader()}); 

  }
  addRoom(newRoom: Room, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/hotels/addRoom/'+id,newRoom,{headers: this.auth.createAuthorizationTokenHeader()}); 

  }
  deleteService(id:number): Observable<any>{
      return this.http.post('http://localhost:8080/hotels/deleteService/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 

  }
  changeService(service: AdditionalServiceForHotel, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/changeService/'+id,service,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  deleteRoom(id:number): Observable<any>{
      return this.http.post('http://localhost:8080/hotels/deleteRoom/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  
  changeRoom(room: Room, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/changeRoom/'+id,room,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  searchRooms(res : ReservationRoom, id : number, cenaod : number, cenado: number) : Observable<any> {
      return this.http.post('http://localhost:8080/rooms/searchRooms/'+id + "/"+cenaod + "/" +cenado,res); 
  };
  bookRoom(res : ReservationRoom) : Observable<any> {
      return this.http.post('http://localhost:8080/rooms/bookRoom',res); 
  };
  
  getLastWeekReservations(id: number, dateToday : string): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getLastWeekReservations/'+id+"/"+dateToday,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  
  getAllReservations(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getAllReservations/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  getAllRatingsHotel(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getAllRatingsHotel/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  getRatingRoom(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/rooms/getRatingRoom/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  getHotelRevenue(id: number, od :string, Do : string) : Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getHotelRevenue/'+id +'/' + od +'/' + Do,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
}
