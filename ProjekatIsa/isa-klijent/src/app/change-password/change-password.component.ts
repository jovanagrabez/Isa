import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from '../models/User';
import { Role } from '../models/Role';
import { Token } from '../models/Token';
import { AuthService } from '../auth/service/auth.service';
import { Subscription } from 'rxjs/Subscription';
import { UserService} from '../../app/services/user-service/user.service';
import { AuthServiceService } from '../../app/services/auth-service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
    
    token: string;
    user: User = new User();
    currentUser: any;

    currentPass : any;
    newPass : any;
    confPass : any;

constructor(private userService: UserService,private router : Router, private auth: AuthServiceService) { }

  ngOnInit() {
      this.token = this.auth.getJwtToken();
      if (!this.token) {    
          console.log('----KORISNIK NIJE ULOGOVAN---');
        } else {
          console.log('----KORISNIK JE ULOGOVAN----');     
          this.userService.getLogged(this.token).subscribe(data => {         
              this.currentUser = data;
              console.log(this.currentUser);
          });
        }
  }
  changePassword(){
      if (this.newPass != this.confPass) {
          alert("Niste uneli istu sifru, kao potvrdu!");
          return;
      }
      if(this.newPass.length <= 5 || this.newPass.length > 15){
        alert("Sifra  mora imati izmedju 6 i 15 karaktera");
        return false;
      }
      this.user.email = this.currentUser.email;
      this.user.password = this.currentPass;
      this.user.passwordConfirm = this.newPass;
      this.userService.changePassword(this.user, this.currentUser.id).subscribe(data=>{
          alert("Uspjesno promjenjena lozinka");
          this.router.navigateByUrl('/home');
      });
  }

}
