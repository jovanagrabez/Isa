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
import { AuthServiceService } from '../services/auth-service.service';
import { CarReservation } from '../models/CarReservation';
import { Category } from '../models/Category';
import { CategoryServiceService } from '../services/cat-service/category-service.service';



@Component({
  selector: 'app-fil-cars',
  templateUrl: './fil-cars.component.html',
  styleUrls: ['./fil-cars.component.css']
})
export class FilCarsComponent implements OnInit {

  private currentFil : Filijale;
  car : Car[];
  user : User = new User();

  nocarAdmin : boolean;
  noUser : boolean;
  newFil : Filijale = new Filijale();
  newCar : Car = new Car();
  newCar2 : Car = new Car();
  token: string;  
  logged: boolean;
  notLogged: boolean;
  changeCar : Car = new Car();
  reservation : CarReservation = new CarReservation();
  totalAccPrice : number;
  c : Car = new Car();
  id : number;
  kategorije : Array<Category>;
  preuzeto  : Date; 
  vraceno : Date;
  rez : CarReservation = new CarReservation();
  pronadjenaVozila : Array<Car>;
  selektovana : Category;


  constructor(private router: Router, private ngZone : NgZone, private modalService: NgbModal,
  private filService : FilijaleServiceService, private carService : CarServiceService,
  private auth: AuthServiceService, private categoryService : CategoryServiceService) { }

  ngOnInit() {
      
      
      
      this.token = this.auth.getJwtToken();

      this.user = JSON.parse(localStorage.getItem('user'));
      
      if (!this.token) { 
            this.notLogged = true;
            this.nocarAdmin = true;
            this.noUser = false;

            console.log('----KORISNIK NIJE ULOGOVAN---');
    } else {
      console.log('----KORISNIK JE ULOGOVAN----');     
       if(this.user.roles==null){
            this.nocarAdmin = true;
            this.noUser = false;
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
          }
      
      this.filService.currentFil.subscribe(
        currentFil =>
        {
            this.currentFil = currentFil;
            console.log(currentFil);
            console.log(currentFil.id);

            
            this.carService.getCars(currentFil.id).subscribe(data1=>{
                this.car = data1;
                console.log(data1);
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
            } else if(newCar.car_number==null){
                alert("Morate uneti registarsku oznaku");
            } else if(newCar.price==null) {
                alert("Morate uneti cenu");
            } else if(newCar.prod_year==null) {
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
    
    changeCarClick(c){
        this.changeCar = c;
        document.getElementById('changeCarDiv').removeAttribute('hidden');

        
    }
    
    discardChangeRoomClick(){
          document.getElementById('changeCarDiv').setAttribute("hidden", "true");  
      };
    
    finalChangeCarClick(newCar2) {
          
          console.log(newCar2);
          this.carService.changeCar(newCar2, this.changeCar.id).subscribe(data=>{
              document.getElementById('changeCarDiv').setAttribute("hidden", "true");
              alert("uspjesno");
          });
          //window.location.href = 'http://localhost:4200/rentalCars';
      };
    
    
    bookCarClick(c) {
      this.id = c.id; 
      this.c = c;
      this.totalAccPrice = this.c.price; 
      document.getElementById('bookingDiv').removeAttribute('hidden');
      
  }
    
    isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }
    
    

}
