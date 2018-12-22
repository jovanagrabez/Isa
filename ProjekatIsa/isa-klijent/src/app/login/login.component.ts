import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../app/services/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error:any;
  constructor(private loginService:LoginService,private router:Router) { }
 
  ngOnInit() {
  }

  onSubmit(name : string, password : string,rememberMe : string) {

    alert("iz komponente: " + name+password+rememberMe);
    let response=this.loginService.loginUser({name : name, password : password,rememberMe : rememberMe})
    .subscribe((next) => {
      this.router.navigateByUrl("/view-hotels"); // login succeleed\
      console.log("uspelo");
    }, error => {
      this.error = "Bad credentials"; // or extract smth from <error> object
      console.log(this.error);
   });  
  }

}
