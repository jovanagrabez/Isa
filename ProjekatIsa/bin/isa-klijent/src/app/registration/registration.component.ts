import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../app/services/login.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
        

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit() {
  }
    
    onSubmit(userUserName : string, userPassword : string, userName : string,userLastName : string,userCity : string,userPhoneNumber : string){
  
    
   

}
   } 
