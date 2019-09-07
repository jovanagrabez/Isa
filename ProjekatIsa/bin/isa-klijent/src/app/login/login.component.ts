import { Component, OnInit, Input } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from '../models/User';
import { Token } from '../models/Token';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { AuthService } from '../auth/service/auth.service';
import { AuthLoginInfo } from '../auth/forms/login-info';
import { TokensService } from '../auth/tokens/tokens.service';



import { Subscription } from 'rxjs/Subscription';

import {  UserService} from '../../app/services/user.service';
import { AuthServiceService } from '../../app/services/auth-service.service';





@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  @Input() verified : boolean;
  private loginInfo : AuthLoginInfo;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
              private authService : AuthService, 
              private router: Router, 
              private tokenStorage : TokensService) { }
 
  ngOnInit() {
      
    

  }

login() {
     this.loginInfo = new AuthLoginInfo(
                      this.user.email,
                      this.user.password);
    
            console.log("BLA");
           

    //MILAN: prosirio sam metodu servisa da prosledim kredencijale jer ih nigde ne prosledjujete na server u zahtevu
    this.authService.attemptAuth(this.user.email, this.user.password).subscribe(
      data => {
                  console.log("BLA")  

      

      //this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        localStorage["sent"] = false;
        
            this.router.navigateByUrl('/rentalCars');
        
        /*if(this.roles.includes('CAR_ADMIN')) {
          this.router.navigate(['/rentalCars']);
        }*/
        console.log("BLA")  
        //MILAN: dodao sam cuvanje tokena u localStorage ovde cisto da bih proverio da li ce raditi pozivi
        localStorage.setItem("token", data.accessToken);

      
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  
    this.user = new User();
    
     /*login(email, password) {

         console.log(email);
         console.log(password);
  }*/

  /*login(name : string, password : string, rememberMe : string) {
      alert("iz komponente: " + name+password+rememberMe);
    let response=this.loginService.loginUser({name : name, password : password,rememberMe : rememberMe})
    .subscribe((next) => {
      this.router.navigateByUrl("/view-hotels"); // login succeleed\
      console.log("uspelo");
    }, error => {
      this.error = "Bad credentials"; // or extract smth from <error> object
      console.log(this.error);
   });*/  
  

}
    }
