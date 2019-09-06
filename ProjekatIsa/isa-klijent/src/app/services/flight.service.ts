import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }

  addFlight(flight: any) {
    return this.http.post('http://localhost:8080/flight', flight);
  }

  deleteFlight(id, com_id) {
    return this.http.delete('http://localhost:8080/flight/'.concat(id) + '/'.concat(com_id));

  }
  updateFlight(flight) {
    return this.http.put('http://localhost:8080/flight/update', flight);

  }

  getFlight(id) {
    return this.http.get('http://localhost:8080/flight/'.concat(id));

  }

  updateFlightSeats(flight: any) {
    return this.http.put('http://localhost:8080/flight/seats', flight);
  }
}
