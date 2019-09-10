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

  createReservation2(reservation: any) {

    return this.http.post('http://localhost:8080/flight/resComplete', reservation);


  }

  sendCreatedReservationEmail(reserv: any) {
    return this.http.get('http://localhost:8080/flight/email/'.concat(reserv));
    
  }

  getInvitation(id: any) {
    return this.http.get('http://localhost:8080/flight/invite/'.concat(id));
  }

  deleteInvitation(id: any) {
    return this.http.delete('http://localhost:8080/flight/invite/'.concat(id));
  }
}
