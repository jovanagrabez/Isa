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



  constructor(private resService : ResServiceService,private router: Router,private carService : CarServiceService,private service : ViewRentalCarsService) { 
   }



  ngOnInit() {
        
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
    
    
    reserve(id: number){
    //const idFlight = nesto tu uzimamo id leta
        const startDate = this.rez.startDate;
        console.log(this.rez.startDate.getTime);
        const endDate = this.rez.endDate;
        console.log('rezervacija je uspjesno izvrsena');
        this.resService.fastReservations(-1, id , startDate, endDate,this.user.id).subscribe(data =>{
          this.router.navigateByUrl('/rentalCars');
        });
      }


}
