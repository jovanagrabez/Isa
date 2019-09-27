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
import {SearchFormServices} from '../models/SearchFormServices';
import { DatePipe } from '@angular/common';

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
    searchFormServices : SearchFormServices = new SearchFormServices();
    canBook : boolean;

    //za proveru leta
    flightRes : any[] = [];
    flightReservationId : number;
    //za datume
    pomoc: string;
    pomocDva: string;
    minDatum : Date;

  constructor(private datePipe: DatePipe,private resService : ResServiceService,private router: Router,private hotelService : HotelServiceService,private service : ViewHotelsService) { }

  ngOnInit() {
    //za kontrolu kalendara
      this.pomoc = this.datePipe.transform(Date.now(), 'yyyy-MM-dd');
      console.log(this.pomoc);
      this.pomocDva = this.pomoc;
      this.minDatum = new Date(this.pomoc);
      
      this.canBook = false;
      this.user = JSON.parse(localStorage.getItem('user'));
      this.service.currentHotel.subscribe(
       currentHotel => {
           this.currentHotel = currentHotel;
           console.log(currentHotel);

          this.hotelService.getDiscountRoomsid(this.currentHotel.id).subscribe(data=>{
              this.discount=data;
              console.log(data);
          });
          this.hotelService.getAllMyFlights(this.user.id).subscribe(data=>{
              this.flightRes  = data;
              console.log("moji letovi: ");
              console.log(data);
          });
       });
  }
  //kontrola datum
  intervalDatuma(){
      this.pomocDva = (<HTMLInputElement>document.getElementById("datMin")).value;
  }
  pretraga(){

     if(this.isBlank(this.flightReservationId)){
          alert("Morate odabrati let");
     }
     else{
 
      this.searchFormServices.city = this.currentHotel.city;
      this.searchFormServices.nameHotel = this.currentHotel.name;
      console.log(this.searchFormServices.nameHotel);
      this.hotelService.searchDiscountRooms(this.searchFormServices,this.flightReservationId).subscribe(data=>{
          console.log('pretrazeni hoteli');
          console.log(data);
          if (data==null){
              alert("Datum pocetka rezervacije mora biti jednak ili veci od datuma pocetka leta!")
          }else{
              this.discount=data;
          }
          
          
      });
      this.rez.startDate = this.searchFormServices.startDate;
      this.rez.endDate = this.searchFormServices.endDate;
      this.canBook = true;

    }
  }
  reserve(id: number){
      const startDate = this.rez.startDate;
      console.log(this.rez.startDate.getTime);
      const endDate = this.rez.endDate;
      console.log('rezervacija je uspjesno izvrsena');
      this.resService.fastReservationsHotel(-1, id , startDate, endDate,this.user.id,this.flightReservationId).subscribe(data =>{
          alert("Uspjesno rezervisan hotel!");
          this.router.navigateByUrl('/home');
        });
  }
  
  isBlank(str) {
      return (!str || /^\s*$/.test(str));
    }

}
