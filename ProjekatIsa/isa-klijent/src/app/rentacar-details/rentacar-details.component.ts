import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';



@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {
  
  private currentRentACar : any;
  private serviceArray : any[] = [];
  car : [];
  constructor(private router: Router,private service : ViewRentalCarsService,private ngZone : NgZone, private modalService: NgbModal ) { }

  ngOnInit() {
      
      this.service.currentRentACar.subscribe(
        currentRentACar =>
        {
            this.currentRentACar = currentRentACar;
            console.log(currentRentACar);
            console.log(currentRentACar.id);
            
            this.service.getCars(currentRentACar.id).subscribe(data=>{
            this.car = data;
            console.log(currentRentACar.id + 'usaooo');
      });

        }
     );
      
    
    
 }

}
