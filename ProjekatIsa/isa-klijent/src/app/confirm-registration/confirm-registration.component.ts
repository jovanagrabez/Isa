import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { ActivatedRoute } from '@angular/router';
import { ConfirmServiceService } from 'src/app/services/confirm-registration-service/confirm-service.service';
import { LoginComponent } from 'src/app/login/login.component';




@Component({
  selector: 'app-confirm-registration',
  templateUrl: './confirm-registration.component.html',
  styleUrls: ['./confirm-registration.component.css']
})
export class ConfirmRegistrationComponent implements OnInit {

  
    id : number;
    user : User = new User();
    confirmedUser : User;
    
    constructor(private confirm : ConfirmServiceService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;
    this.confirm.confirmRegistration(this.id).subscribe(data => {this.confirmedUser = data as User}
      
      );
      console.log(this.id);
      window.location.href = "http://localhost:4200/login";

  }

}
