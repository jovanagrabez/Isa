import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import { FilijaleServiceService } from '../services/fil-service/filijale-service.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';



@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {
  
  private currentRentACar : any;
  private serviceArray : any[] = [];
  car : [];
  fil : [];
  private selectedFil: any;
  filijale$ : Object;
          

 
  constructor(private router: Router,private service : ViewRentalCarsService,private ngZone : NgZone, private modalService: NgbModal,private filService : FilijaleServiceService ) { }

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
            this.service.getFilijale(currentRentACar.id).subscribe(data=>{
                data => this.filijale$ = data
                this.fil = data;
                });

        }
     );
      
    
    
 }
    
    
    
    onClickCompanyDetails(Filijale:any) : void {
        this.selectedFil = Filijale;
        this.filService.selectedFil(Filijale);
        this.filService.currentFil.subscribe(
          currentFil=>
          {
              console.log("Rent a car service" + currentFil);
              }
       );
        
        this.router.navigateByUrl('/fil-cars');
    }

}
