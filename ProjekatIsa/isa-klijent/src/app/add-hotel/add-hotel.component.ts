import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';

import { Hotel } from '../models/Hotel';
import { AdditionalServiceForHotel } from '../models/AdditionalServiceForHotel';

@Component({
  selector: 'app-add-hotel',
  templateUrl: './add-hotel.component.html',
  styleUrls: ['./add-hotel.component.css']
})
export class AddHotelComponent implements OnInit {

  hotel : Hotel = new Hotel();  
  addServices: AdditionalServiceForHotel[];
  hideError : boolean;
  errorMessage : string;
  
constructor(private router : Router, private hotelService : HotelServiceService) { }

  ngOnInit() {
      
      this.hideError = true;
     /* this.hotelService.getAllAdditionalServices().subscribe(data=>{
          this.addServices = data;
          console.log('uspjesno preuzeti svi add servisi');
          console.log(data);
      })*/
  }

  addHotel(){
      this.errorMessage = '';
      this.hideError = true;
 
      if (!this.hotel.name) {
        this.hideError = false;
        this.errorMessage = 'Morate uneti naziv hotela!';
      }else if (!this.hotel.city) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti grad hotela!';
      }else if (!this.hotel.address) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti adresu hotela!';
      }else if (!this.hotel.description) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti opis hotela!';
      }
      else {
          this.hotelService.addHotel(this.hotel).subscribe(data=>{
              console.log("uspjesnooooo dodan hotel")
              alert("Uspjesno dodan hotel!")
              window.location.href = 'http://localhost:4200';
          });
          
      }
  }
}
