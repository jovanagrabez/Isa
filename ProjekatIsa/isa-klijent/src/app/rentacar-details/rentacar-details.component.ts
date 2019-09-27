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
import Map from 'ol/Map';
import Tile from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import { DomSanitizer} from '@angular/platform-browser';
import { DatePipe } from '@angular/common';
import { PricingCar } from '../models/PricingCar';


@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {
  
  private currentRentACar : any;
  private serviceArray : any[] = [];
  car : [];
  fil : Filijale[];
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
  canBook : boolean;
    //za mape
    adresa = "";
    finalna = "";
    map;
    
     //za proveru leta
    flightRes : any[] = [];
    flightReservationId : number;
    
    //za pricing
    pricingList : PricingCar[];
    newPricing : PricingCar = new PricingCar();
    newPricing2 : PricingCar = new PricingCar();
    changePricing : PricingCar = new PricingCar();
    currentCar : Car = new Car();

  constructor(private router: Router,private sanitizer:DomSanitizer,private service : ViewRentalCarsService,private ngZone : NgZone, private modalService: NgbModal,
  private filService : FilijaleServiceService,private auth: AuthServiceService,private categoryService : CategoryServiceService,
  private carService : CarServiceService, private resService : ResServiceService ) { }

  ngOnInit() {
      
      this.categoryService.getAll().subscribe(data=>{
          console.log('Sve kategorije' + data);
          this.kategorije = data;
          console.log(data);
          this.canBook = true;
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
            
            
          this.carService.getAll(this.currentRentACar.id).subscribe(data=>{
             console.log('Sve kategorije' + data);
             this.pronadjenaVozila = data;
             console.log(data);
          
          });
            
            
            this.service.getFilijale(this.currentRentACar.id).subscribe(data=>{
                data => this.filijale$ = data
                this.fil = data;
              
                //this.fil = this.pronadjenaVozila.filijale;
                });
                
              this.carService.getAllMyFlights(this.user.id).subscribe(data=>{
                    this.flightRes  = data;
                    console.log("moji letovi: ");
                    console.log(data);
            });

        }
     );  
        this.getAddress();
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
            
         else if (this.token && (this.isBlank(this.flightReservationId))){
              if(this.isBlank(this.flightReservationId)){
              alert("Morate odabrati let");
              }
          }
//       else if(this.rez.pickupPlace != this.pronadjenaVozila.filijale.grad){
//            alert("Morate uneti mesto preuzimanja");
//            }
        else if(this.isBlank(this.cenaOd) && this.isBlank(this.cenaDo))
             { 
         
             console.log("printam id leta " + this.flightReservationId);
              //provera za let
              this.carService.chekIfFlightIsBooked(this.rez, this.flightReservationId).subscribe(data=>{
                  console.log(data);
                  /*if(data==true) {
                      alert("Problemi zbog rezervacije leta. Provjerite da li je" +
                            "datum pocetka leta prije prijave u hotel. Provjerite broj ljudi.");    
                  }*/
              });
            
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
                this.carService.searchCars(this.rez,this.currentRentACar.id,-1,-1).subscribe(data=>{
                    this.pronadjenaVozila=data;
                    this.canBook = false;
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
             this.carService.searchCars(this.rez,this.currentRentACar.id,this.cenaOd,this.cenaDo).subscribe(data=>{
                    this.pronadjenaVozila=data;
                    this.canBook = false;
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
            this.resService.reserveCar(this.rez,this.flightReservationId).subscribe(data=>{
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
    inicijalizujMapu() {
        console.log("usao u mapu");
        this.map = new Map({
          target: 'map',
          layers: [
            new Tile({
              source: new OSM()
            })
          ],
        view: new View({
        center: [45.2588889, 19.81661],
        zoom: 1
    })
   });
    }
    getAddress() {
        this.adresa += this.currentRentACar.adress.replace(/ /g, '%20');
        this.finalna += "https://maps.google.com/maps?q=" + this.adresa + "&t=&z=13&ie=UTF8&iwloc=&output=embed";
        console.log("adresse");
        console.log(this.adresa);
        console.log(this.finalna);
      }
    
     reportClick(){
        this.service.selectRentACar(this.currentRentACar);
        this.service.currentRentACar.subscribe(
                currentRentACar => 
                {
                    console.log("Current service: " +  currentRentACar);
                }
            );

            this.router.navigateByUrl('/serviceReport');
         }
    
     addPricingClick(){

        document.getElementById('addPricingDiv').removeAttribute('hidden'); 
        };
    discardPricingClick(){
        document.getElementById('addPricingDiv').setAttribute("hidden", "true");  
         };
    
    finalAddPricingClick(newPricing){
        console.log(newPricing);
        if (newPricing.price==null){
            alert("Morate uneti cenu");
        }else if( newPricing.dateTo==null){
            alert("Morate uneti datum");
        }else if( newPricing.dateFrom==null){
            alert("Morate uneti datum");
        }else{
            this.carService.addPricing(newPricing, this.currentCar.id).subscribe(data=>{
                if(data==null){
                    alert("Neuspjesno dodano! Izaberite validan datum!");  
                }else{
                    
                
                alert("Uspjesno dodana stavka u cenovnik!");
                window.location.href = 'http://localhost:4200/rentalCars'; 
                }
            });
            document.getElementById('addPricingDiv').setAttribute("hidden", "true");  
        }
     };
    
    pricingCarClick(r : Car) {
         this.carService.getAllPricing(r.id).subscribe(data=>{
             this.pricingList = data;
             console.log(data);
         });
         this.currentCar = r;
         document.getElementById('showPricingDiv').removeAttribute('hidden'); 
     };
    
    
     //rad sa pricingom
      changePricingClick(p) {
          this.changePricing = p;
          document.getElementById('changePricingDiv').removeAttribute('hidden');
      };
      finalChangePricingClick(newPricing2){
          console.log(newPricing2);
          this.carService.changePricing(newPricing2, this.changePricing.id).subscribe(data=>{
              document.getElementById('changePricingDiv').setAttribute("hidden", "true");
              alert("uspjesno");
          });
          
      };
      discardChangePricingClick(){
          document.getElementById('changePricingDiv').setAttribute("hidden", "true");  
      };

}
