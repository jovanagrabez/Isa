import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from './services/user-service/user.service';
import { AuthServiceService } from './services/auth-service.service';
import { User } from './models/User';
import { Role } from './models/Role';
import {ViewportScroller} from '@angular/common';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Travel';
  logged: boolean;
  notLogged: boolean;
  token: string;
  podatak: object;
  user: User = new User();
  roles: Role[];
  idlogged : number;
  nosystemAdmin : boolean;
  nohotelAdmin: boolean;
  noavioAdmin: boolean;
  nocarAdmin: boolean;
  noUser: boolean;

  constructor(private userService: UserService,private router : Router, private route: ActivatedRoute, private auth: AuthServiceService) {  }

  ngOnInit() {
    this.token = this.auth.getJwtToken();
    
    if (!this.token) {
      this.notLogged = true;
      this.nosystemAdmin = true;
      this.nohotelAdmin = true;
      this.noavioAdmin = true;
      this.nocarAdmin = true;
      this.noUser = true;
      console.log('----KORISNIK NIJE ULOGOVAN---');
    } else {
      console.log('----KORISNIK JE ULOGOVAN----');     
      this.logged = true;
      this.userService.getLogged(this.token).subscribe(data => {
      this.pathToList(data);
      var currentUser=data as User;
      console.log('Token je ');
      console.log(this.token);
      console.log(data);
      console.log('Uloga trenutnog usera');
      console.log(currentUser.roles);
      if (currentUser.firstLogin==false) {
          alert("Morate prvo promeniti lozinku!");
          this.router.navigateByUrl('/change-password');
      }
      });
     }
  }
    
      pathToList(data)
  {
    this.user = data as User;  
    this.roles = this.user.roles;
    if(this.roles == null){
        this.nosystemAdmin = true;
        this.nohotelAdmin = true;
        this.noavioAdmin = true;
        this.nocarAdmin = true;
        this.noUser = true;

    }
    for (var i=0; i<this.roles.length; i++) {
    
    console.log('ime uloge: ');
    console.log(this.roles[i].name.toString());
    
    if(this.roles[i].name.toString()=== 'SYSTEM_ADMIN'){
        this.nosystemAdmin = false;
        this.nohotelAdmin = true;
        this.noavioAdmin = true;
        this.nocarAdmin = true;
        this.noUser = true;


    }
    else if(this.roles[i].name.toString() === 'HOTEL_ADMIN'){
        this.nohotelAdmin = false;
        this.nosystemAdmin = true;
        this.noavioAdmin = true;
        this.nocarAdmin = true;
        this.noUser = true;

    }
    else if(this.roles[i].name.toString() === 'AVIO_ADMIN'){
        this.nohotelAdmin = true;
        this.nosystemAdmin = true;
        this.noavioAdmin = false;
        this.nocarAdmin = true;
        this.noUser = true;

    }
   
    else if(this.roles[i].name.toString() === 'CAR_ADMIN'){
    this.nohotelAdmin = true;
    this.nosystemAdmin = true;
    this.noavioAdmin = true;
    this.nocarAdmin = false;
    this.noUser = true;

    }
    else{
    console.log('user je');
        this.nosystemAdmin = true;
        this.nohotelAdmin = true;
        this.noavioAdmin = true;
        this.nocarAdmin = true;
        this.noUser = false;
    }
}
   
    //this.idlogged=this.user.id;
    //document.getElementById("listCertificates").setAttribute("href", "/list-of-certificates/" + this.id_logged);
  }
    
    
logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4200');
    this.auth.removeJwtToken();    
   // localStorage.setItem('user', JSON.stringify(null));
    this.notLogged = true;
    this.logged = false;
    this.nosystemAdmin = true;
    this.nohotelAdmin = true;
    this.noavioAdmin = true;
    this.nocarAdmin = true;
   
  }
}
