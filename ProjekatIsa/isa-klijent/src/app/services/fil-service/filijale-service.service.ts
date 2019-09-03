import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Filijale } from '../../models/Filijale';
import { AuthServiceService } from '../auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class FilijaleServiceService {

  constructor(private http: HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    addFilijale(fil : Filijale) : Observable<any> {
          return this.http.post('http://localhost:8080/filijale/addFilijale',fil,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
}
