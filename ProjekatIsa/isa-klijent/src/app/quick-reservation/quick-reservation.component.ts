import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarServiceService } from '../services/car-service/car-service.service';
import { Discount } from '../models/Discount';
import { Car } from '../models/Car';

@Component({
  selector: 'app-quick-reservation',
  templateUrl: './quick-reservation.component.html',
  styleUrls: ['./quick-reservation.component.css']
})
export class QuickReservationComponent implements OnInit {
        
  discountCars : Discount = new Discount();
  cars : Car = new Car();
    
  discount : Discount[] = [];


  constructor(private router: Router,private carService : CarServiceService) { 
//  this.discountCars = { id: null,dateFrom: '',  dateTo: '', discount:null,car : {id: null, name:'', car_number: null,price:null,average_rating:null,prod_year:null,rentalcars:null,filijale:null,category:null}};
//  this.cars = {id: null, name:'', car_number: null,price:null,average_rating:null,prod_year:null,rentalcars:null,filijale:null,category:null};
  }



  ngOnInit() {
      
        this.cars.name = this.discountCars.car.name;
        this.cars.price = this.discountCars.car.price;
    
        this.carService.getDiscountCars().subscribe(data=>{
            this.discount=data;
            console.log(data);
            //console.log(this.discountCars.id);
            
            this.carService.getCarById(1).subscribe(data=>{
                this.cars = data;
                
                console.log(this.cars.name);
                
                this.cars = this.discountCars.car;
                console.log(this.discountCars.car.name);
                console.log(data);
                
                });
            
            
            
            });
  }


}
