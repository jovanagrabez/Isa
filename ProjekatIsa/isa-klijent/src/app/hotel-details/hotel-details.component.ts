import { Component, OnInit, NgZone } from '@angular/core';

import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

	private currentHotel: any;

	private hotelsArray : any[] = [];
	private number_OfRoom : any;

    constructor(private router: Router, private viewHotelsService : ViewHotelsService,private ngZone : NgZone, private modalService: NgbModal) { }

    ngOnInit() {
  
    this.viewHotelsService.currentHotel.subscribe(
      currentHotel => 
      {
        this.currentHotel = currentHotel;
        console.log(currentHotel);
  	  }
           
    );
      }
  

}
