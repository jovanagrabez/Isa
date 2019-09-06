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
import { Role } from '../models/Role';
import { AuthServiceService } from '../services/auth-service.service';
import { CarReservation } from '../models/CarReservation';
import { Category } from '../models/Category';
import { CategoryServiceService } from '../services/cat-service/category-service.service';
import { Car } from '../models/Car';
import { CarServiceService } from '../services/car-service/car-service.service';
import { ResServiceService } from '../services/res-service/res-service.service';



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
  roles: Role[];
  token: string;  
  logged: boolean;
  notLogged: boolean;
  rez : CarReservation = new CarReservation();
  kategorije : Array<Category>;
  preuzeto  : Date; 
  vraceno : Date;
  pronadjenaVozila : Array<Car>;
  selektovana : Category;
  brojDana : number;
  cenaDo : number;
  cenaOd : number;


  constructor(private router: Router,private service : ViewRentalCarsService,private ngZone : NgZone, private modalService: NgbModal,
  private filService : FilijaleServiceService,private auth: AuthServiceService,private categoryService : CategoryServiceService,
  private carService : CarServiceService, private resService : ResServiceService ) { }

  ngOnInit() {
      
      this.categoryService.getAll().subscribe(data=>{
          console.log('Sve kategorije' + data);
          this.kategorije = data;
          console.log(data);
          
          });
      this.token = this.auth.getJwtToken();

      this.user = JSON.parse(localStorage.getItem('user'));
      
      if (!this.token) { 
            this.notLogged = true;
            this.nocarAdmin = true;
            console.log('----KORISNIK NIJE ULOGOVAN---');
    } else {
      console.log('----KORISNIK JE ULOGOVAN----');     
       if(this.user.roles==null){
            this.nocarAdmin = true;
        } 
        for (var i=0; i<this.user.roles.length; i++) {
            if(this.user.roles[i].name.toString() === 'CAR_ADMIN'){
                this.nocarAdmin = false;
            }  
            else{
            this.nocarAdmin = true;
            }
        }
          }
        
      
      this.service.currentRentACar.subscribe(
        currentRentACar =>
        {
            this.currentRentACar = currentRentACar;
            console.log(currentRentACar);
            console.log(currentRentACar.id);
            
            
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
    
    
    pretraga(){
        this.vraceno = new Date(this.rez.endDate);
        this.preuzeto = new Date(this.rez.startDate);
        
        if(this.isBlank(this.rez.startDate)){
            alert("Morate odabrati datum preuzimanja");

            }
        else if(this.isBlank(this.rez.endDate)){
            alert("Morate odabrati datum vracanja");
            }
        else if(this.isBlank(this.rez.pickupPlace)){
            alert("Morate uneti mesto preuzimanja");
            }
        else if(this.isBlank(this.rez.returnPlace)){
            alert("Morate uneti mesto vracanja");
            }
        else if(this.isBlank(this.rez.category)){
            alert("Morate odabrati kategoriju");
            }
        else if(this.isBlank(this.rez.numPeople)){
            alert("Morate uneti broj putnika");
            }
        else if(this.preuzeto>this.vraceno){
            alert("Neispravni datumi");
            }
        else if(this.isBlank(this.cenaOd) && this.isBlank(this.cenaDo))
             { 
            
            var proba = Math.abs(this.vraceno.getTime() - this.preuzeto.getTime())
            this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24)); 
            this.kategorije.forEach(element=> {
                if(element.mark == this.rez.category)
                {
                    this.selektovana = element;
                    }
                });
            
            if(this.rez.numPeople > this.selektovana.seats)
            {
               alert("Za odabranu kategoriju je dozvoljeno" + this.selektovana.seats + 'pitnika');

            }
            
            else {
                console.log(this.currentRentACar.id);
                this.carService.searchCars(this.rez.startDate, this.rez.endDate,this.currentRentACar.id,this.rez.category,-1,-1).subscribe(data=>{
                    this.pronadjenaVozila=data;
                    });
                
                }
        
        
        } else 
            {
            
             if(this.isBlank(this.cenaOd))
        {
            this.cenaDo = this.cenaDo; 
        }else if(this.isBlank(this.cenaDo))
        {
            this.cenaOd = this.cenaOd;
        }else
        {
            
        }
             var proba = Math.abs(this.vraceno.getTime() - this.preuzeto.getTime())
             this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24)); 
             this.carService.searchCars(this.rez.startDate, this.rez.endDate,this.currentRentACar.id,this.rez.category,this.cenaOd,this.cenaDo).subscribe(data=>{
                    this.pronadjenaVozila=data;
                    });
            }
     }
    
    isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }
    
    
    reserve(c : Car){
        
        if(!this.token){
            alert("Morate biti ulogovani kako bi izvrsili proces rezervacije");
            }
        
        else{
           this.rez.car = c;
           this.rez.user = this.user;
           console.log("rezervaciju je izvrsio" + this.rez.user.email);
            this.resService.reserveCar(this.rez).subscribe(data=>{
                alert("rezervacija uspesna");
                window.location.href="rentalCars/";

                });
 
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
