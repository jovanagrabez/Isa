import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { FilijaleServiceService } from '../services/fil-service/filijale-service.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { User } from '../models/User';
import { Filijale } from '../models/Filijale';
import { Car } from '../models/Car';
import { CarServiceService } from '../services/car-service/car-service.service';



@Component({
  selector: 'app-fil-cars',
  templateUrl: './fil-cars.component.html',
  styleUrls: ['./fil-cars.component.css']
})
export class FilCarsComponent implements OnInit {

  private currentFil : any;
  car : [];
  user : User = new User();

  nocarAdmin : boolean;
  noUser : boolean;
  newFil : Filijale = new Filijale();
  newCar : Car = new Car();



  constructor(private router: Router, private ngZone : NgZone, private modalService: NgbModal,
  private filService : FilijaleServiceService, private carService : CarServiceService) { }

  ngOnInit() {
      
      this.user = JSON.parse(localStorage.getItem('user'));

       if(this.user.roles==null){
            this.nocarAdmin = true;
            this.noUser = true;
        } 
        for (var i=0; i<this.user.roles.length; i++) {
            if(this.user.roles[i].name.toString() === 'CAR_ADMIN'){
                this.nocarAdmin = false;
                this.noUser = true;
            }  
            else{
            this.nocarAdmin = true;
            this.noUser = false;
            }
        }
      
      this.filService.currentFil.subscribe(
        currentFil =>
        {
            this.currentFil = currentFil;
            console.log(currentFil);
            
            this.filService.getCars(currentFil.id).subscribe(data=>{
                this.car = data;
                console.log(currentFil.id + 'usao'); 
                });
            });
  }
    
    
     changeClick(){
        document.getElementById('changeDiv').removeAttribute('hidden');

    };
    discardClick() {
        document.getElementById('changeDiv').setAttribute('hidden', 'true');  
    };
    
    finalChangeClick(newFil){
        console.log(newFil);
        this.filService.changeFil(newFil, this.currentFil.id).subscribe(data=>{
            document.getElementById('changeDiv').setAttribute("hidden", "true");
            alert("uspjesno");
            });
         window.location.href = 'http://localhost:4200/rentalCars';
        }
    deleteClick() {
        if(confirm("Da li ste sigurni da zelite da obrisete filijalu?")){
            this.filService.deleteFil(this.currentFil.id).subscribe(data=>{
                alert("Uspjesno obrisano!");
                window.location.href = 'http://localhost:4200/rentalCars';
                });
        }else{}
    }
    
    addCarClick() {
        document.getElementById('addCarDiv').removeAttribute('hidden');
    };
    
    discardCarClick() {
        document.getElementById('addCarDiv').setAttribute("hidden","true");

     };
    
    finalAddCarClick(newCar){
        console.log(newCar);
        
        if(newCar.name==null){
            alert("Morate uneti naziv");
            } else if(newCar.regnumber==null){
                alert("Morate uneti registarsku oznaku");
            } else if(newCar.price==null) {
                alert("Morate uneti cenu");
            } else if(newCar.prodYear==null) {
                alert("Morate uneti godinu proizvodnje");
            }else {
                this.filService.addCar(newCar,this.currentFil.id).subscribe(data=>{
                    alert("Vozilo je uspesno dodano!");
                    window.location.href = 'http://localhost:4200/rentalCars';  
                    });
                document.getElementById('addCarDiv').setAttribute("hidden", "true");  

            }
        
     }
    
    deleteCarClick(c){
        console.log(c.id);
        if(confirm("Da li ste sigurni da zelite da obrisete vozilo?")){
            this.carService.deleteCar(c.id).subscribe(data=>{
                alert("Uspjesno obrisano!");
                window.location.href = 'http://localhost:4200/rentalCars';
                });
        }else{}
        
        }
    
    

}
