import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';
import { DiscountHotel } from '../models/DiscountHotel';
import { Room } from '../models/Room';
import { ViewHotelsService } from '../services/view-hotels.service';
import { ReservationRoom } from '../models/ReservationRoom';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import { User } from '../models/User';
import { ResServiceService } from '../services/res-service/res-service.service';

@Component({
  selector: 'app-quick-reservation-hotel',
  templateUrl: './quick-reservation-hotel.component.html',
  styleUrls: ['./quick-reservation-hotel.component.css']
})
export class QuickReservationHotelComponent implements OnInit {

    discountRooms : DiscountHotel = new DiscountHotel();
    room : Room = new Room();
    public form: FormGroup;  
    discount : Array<DiscountHotel>;
    private currentHotel : any;
    rez : ReservationRoom = new ReservationRoom();
    public searchFromDate: AbstractControl;
    public searchToDate: AbstractControl;
    user : User = new User();

  constructor(private resService : ResServiceService,private router: Router,private hotelService : HotelServiceService,private service : ViewHotelsService) { }

  ngOnInit() {
      this.user = JSON.parse(localStorage.getItem('user'));
      this.service.currentHotel.subscribe(
       currentHotel => {
           this.currentHotel = currentHotel;
           console.log(currentHotel);

          this.hotelService.getDiscountRooms(this.currentHotel.id).subscribe(data=>{
              this.discount=data;
              console.log(data);
          });
       });
  }
  
  reserve(id: number){
      const startDate = this.rez.startDate;
      console.log(this.rez.startDate.getTime);
      const endDate = this.rez.endDate;
      console.log('rezervacija je uspjesno izvrsena');
      this.resService.fastReservationsHotel(-1, id , startDate, endDate,this.user.id).subscribe(data =>{
          alert("Uspjesno rezervisan hotel!");
          this.router.navigateByUrl('/home');
        });
  }

}
