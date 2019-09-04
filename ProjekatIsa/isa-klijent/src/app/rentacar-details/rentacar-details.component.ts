import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import { FilijaleServiceService } from '../services/fil-service/filijale-service.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { User } from '../models/User';
import { RentACar } from '../models/RentACar';
import { Filijale } from '../models/Filijale';





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
  nocarAdmin : boolean;
  user : User = new User();
  newService : RentACar = new RentACar(); 
  newFil : Filijale = new Filijale();
    
  constructor(private router: Router,private service : ViewRentalCarsService,private ngZone : NgZone, private modalService: NgbModal,private filService : FilijaleServiceService ) { }

  ngOnInit() {
      
      this.user = JSON.parse(localStorage.getItem('user'));

       if(this.user.roles==null){
            this.nocarAdmin = true;
            this.user = null;
        } 
        for (var i=0; i<this.user.roles.length; i++) {
            if(this.user.roles[i].name.toString() === 'CAR_ADMIN'){
                this.nocarAdmin = false;
            }  
            else{
            this.nocarAdmin = true;
            }
        }
      
      this.service.currentRentACar.subscribe(
        currentRentACar =>
        {
            this.currentRentACar = currentRentACar;
            console.log(currentRentACar);
            console.log(currentRentACar.id);
            
            this.service.getCars(this.currentRentACar.id).subscribe(data=>{
            this.car = data;
            console.log(currentRentACar.id + 'usaooo');
      });
            this.service.getFilijale(this.currentRentACar.id).subscribe(data=>{
                data => this.filijale$ = data
                this.fil = data;
                });

        }
     );       
 };
   
    
    changeClick(){
        document.getElementById('changeDiv').removeAttribute('hidden');

    };
    discardClick() {
        document.getElementById('changeDiv').setAttribute('hidden', 'true');  
    };
    
    finalChangeClick(newService){
        console.log(newService);
        this.service.changeService(newService, this.currentRentACar.id).subscribe(data=>{
            document.getElementById('changeDiv').setAttribute("hidden", "true");
            alert("uspjesno");
            });
         window.location.href = 'http://localhost:4200/rentalCars';
        }
    deleteClick() {
        if(confirm("Da li ste sigurni da zelite da obrisete servis?")){
            this.service.deleteService(this.currentRentACar.id).subscribe(data=>{
                alert("Uspjesno obrisano!");
                window.location.href = 'http://localhost:4200/rentalCars';
                });
        }else{}
    }
    
     addFilClick() {
        document.getElementById('addFilDiv').removeAttribute('hidden');
    };
    
    discardFilClick() {
        document.getElementById('addFilDiv').setAttribute("hidden","true");

     };
    
    finalAddFilClick(newFil){
        console.log(newFil);
        
        if(newFil.grad==null){
            alert("Morate uneti vrednost za polje grad");
            } else if(newFil.drzava==null){
                alert("Morate uneti vrednost za polje drzava");
            } else if(newFil.adresa==null) {
                alert("Morate uneti vrednost za polje adresa");
            } else {
                this.service.addFil(newFil,this.currentRentACar.id).subscribe(data=>{
                    alert("Filijala je uspesno dodana!");
                    window.location.href = 'http://localhost:4200/rentalCars';  
                    });
                document.getElementById('addFilDiv').setAttribute("hidden", "true");  

            }
        
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
