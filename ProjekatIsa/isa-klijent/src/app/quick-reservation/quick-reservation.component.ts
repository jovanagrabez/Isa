import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarServiceService } from '../services/car-service/car-service.service';
import { Discount } from '../models/Discount';
import { Car } from '../models/Car';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import { CarReservation } from '../models/CarReservation';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import { ResServiceService } from '../services/res-service/res-service.service';
import { User } from '../models/User';
import { DatePipe } from '@angular/common';
import {SearchFormServices} from '../models/SearchFormServices';

@Component({
  selector: 'app-quick-reservation',
  templateUrl: './quick-reservation.component.html',
  styleUrls: ['./quick-reservation.component.css']
})
export class QuickReservationComponent implements OnInit {
        
  discountCars : Discount = new Discount();
  cars : Car = new Car();
  public form: FormGroup;  
  discount : Array<Discount>;
  private currentRentACar : any;
  rez : CarReservation = new CarReservation();
  public searchFromDate: AbstractControl;
  public searchToDate: AbstractControl;
  user : User = new User();
  searchFormServices : SearchFormServices = new SearchFormServices();
  canBook : boolean;
  //za datume
  pomoc: string;
  pomocDva: string;
  minDatum : Date;
  //za proveru leta
  flightRes : any[] = [];
  flightReservationId : number;
  brojDana : number;
  preuzeto  : Date; 
  vraceno : Date;



  constructor(private datePipe: DatePipe,private resService : ResServiceService,private router: Router,private carService : CarServiceService,private service : ViewRentalCarsService) { 
   }



  ngOnInit() {
        this.pomoc = this.datePipe.transform(Date.now(), 'yyyy-MM-dd');
        console.log(this.pomoc);
        this.pomocDva = this.pomoc;
        this.minDatum = new Date(this.pomoc);
      
        this.canBook = false;
        this.user = JSON.parse(localStorage.getItem('user'));
        console.log(this.user);

      
        this.service.currentRentACar.subscribe(
        currentRentACar =>
        {
        this.currentRentACar = currentRentACar;
            console.log(currentRentACar);
            console.log(currentRentACar.id);
    
        this.carService.getDiscountCars(this.currentRentACar.id).subscribe(data=>{
            this.discount=data;
            console.log(data);
            
            
             }); 
       this.carService.getAllMyFlights(this.user.id).subscribe(data=>{
              this.flightRes  = data;
              console.log("moji letovi: ");
              console.log(data);
          });           
            
            
            });
  }
   
    intervalDatuma(){
      this.pomocDva = (<HTMLInputElement>document.getElementById("datMin")).value;
  }
    
    
   pretraga(){
       
      if(this.isBlank(this.flightReservationId)){
          alert("Morate odabrati let");
      }
      else{

      this.searchFormServices.city = this.currentRentACar.city;
      this.searchFormServices.name = this.currentRentACar.name;
      this.carService.searchDiscountCars(this.searchFormServices,this.flightReservationId).subscribe(data=>{
          console.log('pretrazenii');
          this.discount=data;
          console.log(data);
      });
      this.rez.startDate = this.searchFormServices.startDate;
      this.rez.endDate = this.searchFormServices.endDate;
      this.canBook = true;
      this.vraceno = new Date(this.rez.endDate);
      this.preuzeto = new Date(this.rez.startDate);
      var proba = Math.abs(this.vraceno.getTime() - this.preuzeto.getTime())
            this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24)); 
      }
    }
    
    reserve(id: number){
    //const idFlight = nesto tu uzimamo id leta
        const startDate = this.rez.startDate;
        console.log(this.rez.startDate.getTime);
        const endDate = this.rez.endDate;
        console.log('rezervacija je uspjesno izvrsena');
        this.resService.fastReservations(-1, id , startDate, endDate,this.user.id,this.flightReservationId).subscribe(data =>{
          alert("Uspjesno rezervisano vozilo!");
          this.router.navigateByUrl('/rentalCars');
        });
      }
    
     isBlank(str) {
      return (!str || /^\s*$/.test(str));
    }


}
