import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from '../models/User';
import { Role } from '../models/Role';
import { Token } from '../models/Token';
//import { routerNgProbeToken } from '@angular/router/src/router_module';
import { AuthService } from '../auth/service/auth.service';
import { AuthLoginInfo } from '../auth/forms/login-info';
import { TokensService } from '../auth/tokens/tokens.service';
import { Subscription } from 'rxjs/Subscription';
import { UserService} from '../../app/services/user-service/user.service';
import { AuthServiceService } from '../../app/services/auth-service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  htmlStr: string;

  @Input() verified : boolean;
  private loginInfo : AuthLoginInfo;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
    
  role: Array<Role>;
 

  constructor(private userService : UserService,
              private auth : AuthServiceService, 
              private router: Router) { }
 
  ngOnInit() {
    if (this.auth.getJwtToken()) {
      this.isLoggedIn = true;
      this.roles = this.auth.getAuthorities();
      
    }
    

  }

login() {
    
    console.log('Dodavanje' + this.user.email + 'email' + this.user.password);
    
    if (this.checkEmail(this.user.email)) {
          this.userService.loginUser(this.user).subscribe(data => { 
          this.checkUser(data);
          } , err => {this.handleAuthError(err); });    
      } else {
        this.htmlStr = 'The e-mail is not valid.';
      }
      
           }  
    
 
checkUser(logged) {
        let user_token= logged as Token;
        
        if(user_token.accessToken == 'error') {
          this.htmlStr = 'The e-mail or password is not correct.';
        } else {
          this.auth.setJwtToken(user_token.accessToken);
          console.log(user_token.accessToken);
          console.log("prije getLogged");
          if(user_token.accessToken == 'notActivated') {
              alert("Morate verifikovati svoj nalog da bi se mogli prijaviti");
          } else {
          this.userService.getLogged(user_token.accessToken).subscribe(data => {
              console.log("u getLogged");
              var currentUser=data as User; 
              console.log("cuvam u json currentusera: ");
              console.log(data);
              
              //this.auth.saveAuthorities(data.authorities);
              this.isLoginFailed = false;
              this.isLoggedIn = true;
              this.roles = this.auth.getAuthorities();
              console.log('Uloga trenutnog usera');
              console.log(currentUser.roles);
          
              localStorage.setItem('user', JSON.stringify(currentUser));
              localStorage.setItem('role', JSON.stringify(currentUser.roles));

              window.location.href = 'http://localhost:4200';
             
     });
  }
            }
    }
    
    
    
    checkEmail(text): boolean {
    //const patternMail = /\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b/;
    // tslint:disable-next-line:max-line-length
    const patternMail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   // return re.test(String(email).toLowerCase());
    if (!patternMail.test(text)) {
      alert('Incorrect email.');
      return false;
    }
    return true;
  }


  handleAuthError(err: HttpErrorResponse) {
    if (err.status === 404) {
      alert('Entered email is not valid!');
    }
  }
    
    
    }
     /*this.loginInfo = new AuthLoginInfo(
                      this.user.email,
                      this.user.password);
    
            console.log("BLA");
           

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
        //console.log("ulogovan")  
        //MILAN: dodao sam cuvanje tokena u localStorage ovde cisto da bih proverio da li ce raditi pozivi
       // localStorage.setItem("token", data.accessToken);

      
     /* },
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
  

//}
    //}
