import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DestinationServiceService {

  constructor(private http: HttpClient) { }

  addDestination(destination){
    return this.http.post('http://localhost:8080/destination', destination);
  }

  getAllDestinations() {
    return this.http.get('http://localhost:8080/destination');
  }

  deleteDestination(id, com_id) {
    return this.http.delete('http://localhost:8080/destination/'.concat(id) + '/'.concat(com_id));
  }
}
