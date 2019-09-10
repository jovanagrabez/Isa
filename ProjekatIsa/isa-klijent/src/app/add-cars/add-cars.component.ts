import { Component, OnInit } from '@angular/core';
import { Car } from '../models/Car';
import { Category } from '../models/Category';
import { Filijale } from '../models/Filijale';
import { NewCar } from '../models/NewCar';
import { CarServiceService } from '../services/car-service/car-service.service';
import { Router } from '@angular/router';
import { CategoryServiceService } from '../services/cat-service/category-service.service';

@Component({
  selector: 'app-add-cars',
  templateUrl: './add-cars.component.html',
  styleUrls: ['./add-cars.component.css']
})
export class AddCarsComponent implements OnInit {

  car : Car = new Car();
  hideError : boolean;
  errorMessage : string;
  cats : Array<any>;
  filijala : Filijale;
  newC : NewCar = new NewCar();
  category : Category;
  kategorije : Array<Category>;

  constructor(private router : Router,private carService : CarServiceService,private categoryService : CategoryServiceService) { }

  ngOnInit() {
    this.hideError = true;
      
      this.categoryService.getAll().subscribe(data=>{
          console.log('Sve kategorije' + data);
          this.kategorije = data;
          console.log(data);
          });
  }

  addCar() {
      this.errorMessage = '';
      this.hideError = true;
 
     /* if (!this.car.name) {
        this.hideError = false;
        this.errorMessage = 'Morate uneti naziv!';
      }else if (!this.car.regnumber) {
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
        }*/
}
}
