import { Component, OnInit } from '@angular/core';
import { Car } from '../models/Car';
import { CarServiceService } from '../services/car-service/car-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-cars',
  templateUrl: './add-cars.component.html',
  styleUrls: ['./add-cars.component.css']
})
export class AddCarsComponent implements OnInit {

  car : Car = new Car();
  hideError : boolean;
  errorMessage : string;

  constructor(private router : Router,private carService : CarServiceService) { }

  ngOnInit() {
    this.hideError = true;
  }

  addCar() {
        this.errorMessage = '';
      this.hideError = true;
 
      if (!this.car.name) {
        this.hideError = false;
        this.errorMessage = 'Morate uneti naziv!';
      }else if (!this.car.number) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti oznaku!';
      }else if (!this.car.price) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti cenuu!';
      }
      else {
          this.carService.addCar(this.car).subscribe(data=>{
              console.log("uspjesnooooo")
              alert("Uspjesno dodano vozilo!")
              window.location.href = 'http://localhost:4200';
          });
          }
        }

}
