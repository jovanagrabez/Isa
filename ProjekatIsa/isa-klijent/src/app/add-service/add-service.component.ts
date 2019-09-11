import { Component, OnInit } from '@angular/core';
import { RentACar } from '../models/RentACar';
import { Router,ActivatedRoute } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.css']
})
export class AddServiceComponent implements OnInit {
    newService : RentACar = new RentACar();
  constructor(private router : Router,private route : ActivatedRoute, private viewRentalCarsService : ViewRentalCarsService) { }

  ngOnInit() {
  }
addService(){
      
      console.log(this.newService);
        
        if(this.newService.name==null){
            alert("Morate uneti vrednost za polje naziv");
            } else if(this.newService.adress==null){
                alert("Morate uneti vrednost za polje adresa");
            } else if(this.newService.description==null) {
                alert("Morate uneti vrednost za polje opis");
            } else {
                this.viewRentalCarsService.addService(this.newService).subscribe(data=>{
                    alert("Servis je uspesno kreiran!");
                    window.location.href = 'http://localhost:4200/rentalCars';  
                    });

            }
      
      }
}
