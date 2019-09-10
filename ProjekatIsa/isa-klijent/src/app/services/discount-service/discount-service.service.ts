import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Router} from '@angular/router';
import { Observable } from 'rxjs';
import { SystemDiscount } from '../../models/SystemDiscount';

@Injectable({
  providedIn: 'root'
})
export class DiscountServiceService {

  constructor(private http: HttpClient, private router : Router) { }

  getDiscount(id:number) : Observable<any> {
      return this.http.get('http://localhost:8080/discounts/getDiscount/'+id); 
  };
  changeDiscount(sd: SystemDiscount, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/discounts/changeDiscount/'+id,sd); 
  };
  usePoints(points: number, id: number) : Observable<any> {
      return this.http.get('http://localhost:8080/discounts/usePoints/'+id+"/"+points); 
  };
}
