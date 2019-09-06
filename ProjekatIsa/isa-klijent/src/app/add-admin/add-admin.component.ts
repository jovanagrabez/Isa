import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/User';
import { Role } from '../models/Role';
import { UserService } from '../../app/services/user-service/user.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {
    
  hideError : boolean;
  errorMessage : string;
  passwordError : boolean;
  passwordErrorMessage : string;
  user : User = new User();
    type: number;
checkUser: User = new User();

  constructor(private service: UserService,private router:ActivatedRoute) { }

  ngOnInit() {
      this.hideError = true;
      this.passwordError = true;
  }
  checkEmail(text): boolean {
      const patternMail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (!patternMail.test(text)) {
        alert('Incorrect email.');
        return false;
      }
      return true;
    }
  validateUser(){
      console.log('Dodavanje' + this.user.firstName + this.user.password);
      console.log('provera tipa ' + this.type);
      // tslint:disable-next-line:align
      this.errorMessage = '';
      this.hideError = true;
      this.passwordError = true;
      if (!this.type) {
          this.errorMessage = 'Morate uneti tip admina.';
          this.hideError = false;
      }
      else if (!this.user.firstName) {
        this.hideError = false;
        this.errorMessage = 'Name is required.';
      } else if (!this.user.lastName) {
        this.hideError = false;
        this.errorMessage = 'Lastname is required.';
      } else if (!this.user.email) {
        this.hideError = false; 
        this.errorMessage = 'Mail is required.';
      } else if (!this.user.password) {
        this.errorMessage = 'Password is required.';
        this.hideError = false;    
      } else if (!this.type) {
          this.errorMessage = 'Morate uneti tip admina.';
          this.hideError = false;
      }
      
    if (this.hideError == true) {

      this.checkPassword();
      if(!this.checkEmail(this.user.email)){
        this.passwordError = false; 
      }
    }
     if (this.passwordError == true) {
      
         this.service.registerAdmin(this.user, this.type).subscribe(podaci=>{
             alert("Registracija uspesna. Potvrdite mejlom");
             this.checkUser = podaci as User;
             if(!podaci){
               console.log('podaci null');
               this.hideError = false;
               this.errorMessage = 'All fields are required.';
               
             }else if(this.checkUser.email === 'error'){
               console.log('mejl nije ok');
               
               this.hideError = false;
               this.errorMessage = 'Mail is already taken.';
             }else{
               console.log('prebaci u login');
               window.location.href = 'http://localhost:4200';
             }
         });
      }
  }
  checkPassword() {
      if(this.user.password.length < 8){
     this.passwordError = false;
     this.passwordErrorMessage ="Choose password that have at least 8 characters";
   }else if(/\d/.test(this.user.password) == false){
     this.passwordError = false;
     this.passwordErrorMessage ="Choose password that have at least one number";
   }else if(!this.user.password.match(".*[A-Z].*")){
     this.passwordError = false;
     this.passwordErrorMessage ="Choose password that have at least one uppercase letter";
   }
 }
}
