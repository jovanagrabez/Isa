import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Router} from '@angular/router';
import { Observable } from 'rxjs';
import { Hotel } from '../../models/Hotel';
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
}
