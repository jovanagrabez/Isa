import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-rental-cars',
  templateUrl: './view-rental-cars.component.html',
  styleUrls: ['./view-rental-cars.component.css', './animate.css']
})
export class ViewRentalCarsComponent implements OnInit {
        
  private rentacarArray: any;
  private selectedRentACar: any;

  
  private loggedInUser: any;
  private isAdmin: any;
  
  private currentRate = 2.5;
        
    rentalcars$: Object;

  constructor(private router : Router, private viewRentalCarsService : ViewRentalCarsService) { }

  ngOnInit() {
      
  this.viewRentalCarsService.getRentalCars().subscribe(
    data => this.rentalcars$ = data
      );
  }
    
    onClickShowDetails(RentACar:any) : void {
        this.selectedRentACar = RentACar;
        console.log("Rent a car: " + RentACar);  
        

        this.router.navigateByUrl('/rentacar-details');
    }

}
