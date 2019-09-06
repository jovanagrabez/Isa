import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SeatArrangementService {

  constructor(private http: HttpClient) { }

  getSeatArrangement(seatArrangement: any) {
    return this.http.get('http://localhost:8080/seatArrangement/'.concat(seatArrangement));
  }
}
