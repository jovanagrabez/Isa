import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../app.component';
import {UserService} from '../services/user-service/user.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  userRole: any;
  user: any;
  changePassword: any;
  oldPassword: any;
  newPassword: any;
  confirmPassword: any;

  constructor(private appCom: AppComponent, private userService: UserService) {

  this.user = {firstName: '', lastName: '', password: '', email: '', phoneNumber: '', city: ''};
  }

  ngOnInit() {
    this.userService.getLogged(this.appCom.token).subscribe(data => {
    this.user = data;
    });
    }

  save() {
     this.userService.updateUser(this.user).subscribe(u => {
      console.log(this.user);
     });
  }

  showChangePassword() {
    this.changePassword = true;
  }

  passwordChanged() {
    // if (this.newPassword === this.confirmPassword) {
    //   if (this.newPassword !== '' && this.oldPassword !== '') {
    //     this.changePassword = false;
    //     // this.userService.updateUser(this.user).subscribe(u => {
    //     //   this.user = u;
    //     // });
    //     const passwordChanger = { oldPassword: this.oldPassword, newPassword: this.newPassword };
    //     this.userService.changePassword(passwordChanger).subscribe(newp => {
    //       this.user.password = newp;
    //     });
    //   } else {
    //   }
    // } else {
    // }
  }

  cancelPasswordChange() {
    this.changePassword = false;
  }

}
