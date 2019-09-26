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
            
            
            });
  }
   
    intervalDatuma(){
      this.pomocDva = (<HTMLInputElement>document.getElementById("datMin")).value;
  }
    
    
   pretraga(){

      this.searchFormServices.city = this.currentRentACar.city;
      this.searchFormServices.name = this.currentRentACar.name;
      this.carService.searchDiscountCars(this.searchFormServices).subscribe(data=>{
          console.log('pretrazenii');
          this.discount=data;
          console.log(data);
      });
      this.rez.startDate = this.searchFormServices.startDate;
      this.rez.endDate = this.searchFormServices.endDate;
      this.canBook = true;

    }
    
    reserve(id: number){
    //const idFlight = nesto tu uzimamo id leta
        const startDate = this.rez.startDate;
        console.log(this.rez.startDate.getTime);
        const endDate = this.rez.endDate;
        console.log('rezervacija je uspjesno izvrsena');
        this.resService.fastReservations(-1, id , startDate, endDate,this.user.id).subscribe(data =>{
          alert("Uspjesno rezervisano vozilo!");
          this.router.navigateByUrl('/rentalCars');
        });
      }


}
