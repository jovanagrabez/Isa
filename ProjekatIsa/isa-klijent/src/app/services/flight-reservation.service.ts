import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FlightReservationService {

  constructor(private http: HttpClient) { }

  createReservation(reservation: any) {
    return this.http.post('http://localhost:8080/flight/reservations', reservation);


  }
}
