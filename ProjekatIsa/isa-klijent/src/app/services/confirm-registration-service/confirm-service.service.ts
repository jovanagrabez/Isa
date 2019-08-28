import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class ConfirmServiceService {

  constructor(private http : HttpClient) { }
    
    confirmRegistration(name : number){
         console.log("Usao u verifikaciju");
         console.log(name);
         return this.http.get('//localhost:8080/api/confirmMail/' + name);
  }
}
