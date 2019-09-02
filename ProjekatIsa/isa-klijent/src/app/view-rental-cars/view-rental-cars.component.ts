import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-rental-cars',
  templateUrl: './view-rental-cars.component.html',
  styleUrls: ['./view-rental-cars.component.css', '../view-hotels/animate.css']
})
export class ViewRentalCarsComponent implements OnInit {
        
  private selectedRentACar: any;        
  rentalcars$: Object;

  constructor(private router : Router, private viewRentalCarsService : ViewRentalCarsService) { }

  ngOnInit() {
      
  this.viewRentalCarsService.getRentalCars().subscribe(
    data => this.rentalcars$ = data
      );
  }
    
   onClickCompanyDetails(RentACar:any) : void {
        this.selectedRentACar = RentACar;
        this.viewRentalCarsService.selectRentACar(RentACar);
        this.viewRentalCarsService.currentRentACar.subscribe(
          currentRentACar=>
          {
              console.log("Rent a car service" + currentRentACar);
              }
       );
        
        this.router.navigateByUrl('/rentacar-details');
    }

}
