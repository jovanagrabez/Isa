import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Filijale } from '../../models/Filijale';
import { AuthServiceService } from '../auth-service.service';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class FilijaleServiceService {
        
  private h = new BehaviorSubject<any>(null);
  currentFil = this.h.asObservable()

  constructor(private http: HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    addFilijale(fil : Filijale) : Observable<any> {
          return this.http.post('http://localhost:8080/filijale/addFilijale',fil,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    getFilijale(fil : Filijale) : Observable<any> {
          return this.http.post('http://localhost:8080/filijale/getFilijale',fil,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    getCars(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/filijale/getCars',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    
    selectedFil(filijale : any) {
    this.h.next(filijale);
  }
    
    
}
