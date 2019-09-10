import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Filijale } from '../../models/Filijale';
import { Car } from '../../models/Car';
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
    
    changeFil(newFil: Filijale, id: number) : Observable<any> {
        return this.http.post('http://localhost:8080/filijale/changeFil/'+id,newFil,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
  
    deleteFil(id : number) : Observable<any> {
        return this.http.post('http://localhost:8080/filijale/deleteFil',id,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    addCar(newCar: Car, id:number,idKategorije): Observable<any>{
      return this.http.post('http://localhost:8080/filijale/addCar/'+id + "/" + idKategorije,newCar,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    
    selectedFil(filijale : any) {
    this.h.next(filijale);
  }
    
    
}
