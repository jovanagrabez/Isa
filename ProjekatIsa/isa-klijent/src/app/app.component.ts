import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from './services/user-service/user.service';
import { AuthServiceService } from './services/auth-service.service';
import { User } from './models/User'


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
  idlogged : number;
  constructor(private userService: UserService, private route: ActivatedRoute, private auth: AuthServiceService) { }

  ngOnInit() {
    this.token = this.auth.getJwtToken();
  
    if (!this.token) {
      this.notLogged = true;
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
      
      });
     }
  }
    
      pathToList(data)
  {
    this.user = data as User;
    //this.idlogged=this.user.id;
    //document.getElementById("listCertificates").setAttribute("href", "/list-of-certificates/" + this.id_logged);
  }
    
    
logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4200');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
   
  }
}
