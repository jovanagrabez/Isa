import { Component, OnInit, NgZone } from '@angular/core';

import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from '../models/User';


@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

	private currentHotel: any;

	private hotelsArray : any[] = [];
	private number_OfRoom : any;
    user : User = new User();
    nohotelAdmin : boolean;

    constructor(private router: Router, private viewHotelsService : ViewHotelsService,private ngZone : NgZone, private modalService: NgbModal) { }

    ngOnInit() {
  
    this.user = JSON.parse(localStorage.getItem('user'));
    
    for (var i=0; i<this.user.roles.length; i++) {
        if(this.user.roles[i].name.toString() === 'HOTEL_ADMIN'){
            this.nohotelAdmin = false;
        }
    else{
        this.nohotelAdmin = true;
    }
    }
    this.viewHotelsService.currentHotel.subscribe(
      currentHotel => 
      {
        this.currentHotel = currentHotel;
        console.log(currentHotel);
  	  }
           
    );
      }
  

}
