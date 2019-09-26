import { Component, OnInit, NgZone } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';
import { AuthServiceService } from '../services/auth-service.service';
import { DomSanitizer} from '@angular/platform-browser';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from '../models/User';
import { Hotel } from '../models/Hotel';
import { Room } from '../models/Room';
import { Pricing } from '../models/Pricing';
import { AdditionalServiceForHotel } from '../models/AdditionalServiceForHotel';
import { ReservationRoom } from '../models/ReservationRoom';
import Map from 'ol/Map';
import Tile from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import { DiscountServiceService } from '../services/discount-service/discount-service.service';
import { SystemDiscount } from '../models/SystemDiscount';
import { UserService } from '../services/user-service/user.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

	private currentHotel: any;

	private hotelsArray : any[] = [];
	private number_OfRoom : any;
    user : User = new User();
    nohotelAdmin : boolean;
    newHotel : Hotel = new Hotel();
    newRoom : Room = new Room();
    newRoom2 : Room = new Room();
    changeRoom : Room = new Room();
    newService : AdditionalServiceForHotel = new AdditionalServiceForHotel();
    newService2 : AdditionalServiceForHotel = new AdditionalServiceForHotel();
    idServices: Map<number, boolean> = new Map<number, boolean>();
    token: string; 
    cenaDo : number;
    cenaOd : number;
    vraceno: Date;
    preuzeto : Date;
    rooms : Room[];
    services : AdditionalServiceForHotel[];
    changeService : AdditionalServiceForHotel = new AdditionalServiceForHotel();
    reservationRoom : ReservationRoom = new ReservationRoom();
    brojDana : number;
    canBook : boolean;
    isReserved : boolean;
    
    //za discount
    discount : SystemDiscount = new SystemDiscount();
    discount2 : SystemDiscount = new SystemDiscount();
    popust : boolean;
    iskoristiPoene: boolean;
    //za mape
    adresa = "";
    finalna = "";
    map;
    
    //za datume
    pomoc: string;
    pomocDva: string;
    minDatum : Date;
    
    //za proveru leta
    flightRes : any[] = [];
    flightReservationId : number;
    
    //za pricing
    pricingList : Pricing[];
    newPricing : Pricing = new Pricing();
    newPricing2 : Pricing = new Pricing();
    changePricing : Pricing = new Pricing();
    currentRoom : Room = new Room();
    
    constructor(private datePipe: DatePipe,private router: Router,private sanitizer:DomSanitizer,private auth: AuthServiceService, private viewHotelsService : ViewHotelsService,
            private hotelService : HotelServiceService,private ngZone : NgZone,
            private modalService: NgbModal ,  private sds : DiscountServiceService, private us: UserService) { }

    ngOnInit() {
        this.reservationRoom.totalPrice = 0;
        
        //za kontrolu kalendara
        this.pomoc = this.datePipe.transform(Date.now(), 'yyyy-MM-dd');
        console.log(this.pomoc);
        this.pomocDva = this.pomoc;
        this.minDatum = new Date(this.pomoc);
        
        this.user = JSON.parse(localStorage.getItem('user'));
        this.token = this.auth.getJwtToken();
        this.canBook = true;
        
        if(this.user.roles==null){
            this.nohotelAdmin = true;
        } 
        for (var i=0; i<this.user.roles.length; i++) {
            if(this.user.roles[i].name.toString() === 'HOTEL_ADMIN'){
                this.nohotelAdmin = false;
            }  
            else{
            this.nohotelAdmin = true;
            }
        }

        this.viewHotelsService.currentHotel.subscribe(
          currentHotel => 
          {
            this.currentHotel = currentHotel;
            console.log(currentHotel);
            
            this.viewHotelsService.getAllRooms(this.currentHotel.id).subscribe(data=>{
                this.rooms = data;
                console.log(data)
            });
            this.viewHotelsService.getAllServices(this.currentHotel.id).subscribe(data=>{
                this.services = data;
                
                for (var i=0; i<this.services.length; i++){
                this.idServices.set(this.services[i].id, false);
                }
                console.log(data);
            });
            this.hotelService.getAllMyFlights(this.user.id).subscribe(data=>{
                    this.flightRes  = data;
                    console.log("moji letovi: ");
                    console.log(data);
            });
          
          });
          this.getAddress();
          //za popuste
         this.popust = false;
         this.iskoristiPoene = false;
         this.sds.getDiscount(1).subscribe(data=>{
                    this.discount = data;
                    console.log(this.discount);
          });
                this.sds.getDiscount(3).subscribe(data=>{
                    this.discount2 = data;
                    console.log(this.discount2);
          });
       
    };
    //kontrola datum
    intervalDatuma(){
        this.pomocDva = (<HTMLInputElement>document.getElementById("datMin")).value;
    }
    
    changeClick(){
        document.getElementById('changeDiv').removeAttribute('hidden');

    };
    discardClick() {
        document.getElementById('changeDiv').setAttribute('hidden', 'true');  
    };
    finalChangeClick(newHotel) {
        
        console.log(newHotel);
        this.hotelService.changeHotel(newHotel, this.currentHotel.id).subscribe(data=>{
            document.getElementById('changeDiv').setAttribute("hidden", "true");
            alert("uspjesno");
        });
        window.location.href = 'http://localhost:4200/hotels';
    };

    deleteClick() {
        if (confirm("Da li ste sigurni da zelite da obrisete hotel?")){
            this.hotelService.deleteHotel(this.currentHotel.id).subscribe(data=>{
                alert("Uspjesno obrisano!");
                window.location.href = 'http://localhost:4200/hotels';
            });
        }else{
        }
        
    };
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
            this.hotelService.addPricing(newPricing, this.currentRoom.id).subscribe(data=>{
                if(data==null){
                    alert("Neuspjesno dodano! Izaberite validan datum!");  
                }else{
                    
                
                alert("Uspjesno dodan servis!");
                window.location.href = 'http://localhost:4200/hotels'; 
                }
            });
            document.getElementById('addPricingDiv').setAttribute("hidden", "true");  
        }
     };
    addServiceClick() {
        document.getElementById('addServiceDiv').removeAttribute('hidden');
    };
    discardServiceClick(){
        document.getElementById('addServiceDiv').setAttribute("hidden", "true");  
    };
    
    finalAddServiceClick(newService){
        console.log(newService);
        if (newService.name==null){
            alert("Morate uneti naziv servisa");
        }else if( newService.price==null){
            alert("Morate uneti cenu servisa");
        }else{
            this.hotelService.addService(newService, this.currentHotel.id).subscribe(data=>{
                alert("Uspjesno dodan servis!");
                window.location.href = 'http://localhost:4200/hotels'; 
            });
            document.getElementById('addServiceDiv').setAttribute("hidden", "true");  
        }
     };
     //dodavanje sobe
     addRoomClick() {
         document.getElementById('addRoomDiv').removeAttribute('hidden');
     };
     discardRoomClick(){
         document.getElementById('addRoomDiv').setAttribute("hidden", "true");  
     };
     pricingRoomClick(r) {
         this.hotelService.getAllPricing(r.id).subscribe(data=>{
             this.pricingList = data;
         });
         this.currentRoom = r;
         document.getElementById('showPricingDiv').removeAttribute('hidden'); 
     };
     finalAddRoomClick(newRoom){
         console.log(newRoom);
          if( newRoom.price==null){
             alert("Morate uneti prosecnu cenu sobe");
         }else if( newRoom.room_description==null){
             alert("Morate uneti opis sobe");
         }else if( newRoom.capacity==null){
             alert("Morate uneti kapacitet sobe");
         }else{
             this.hotelService.addRoom(newRoom, this.currentHotel.id).subscribe(data=>{
                 alert("Uspjesno dodana soba!");
                 window.location.href = 'http://localhost:4200/hotels'; 
             });
             document.getElementById('addRoomDiv').setAttribute("hidden", "true");  
         }
      };
      
      //funkcije za upravljanje servisima
      deleteServiceClick(s) {
          if (confirm("Da li ste sigurni da zelite da obrisete servis?")){
              this.hotelService.deleteService(s.id).subscribe(data=>{
                  alert("Uspjesno obrisan servis iz hotela!");
                  window.location.href = 'http://localhost:4200/hotels';
              });
              
          }else{
          }
          
      };
      changeServiceClick(s){
          this.changeService = s;
          document.getElementById('changeServiceDiv').removeAttribute('hidden');

      };

      finalChangeServiceClick(newService2) {
          
          console.log(newService2);
          this.hotelService.changeService(newService2, this.changeService.id).subscribe(data=>{
              document.getElementById('changeServiceDiv').setAttribute("hidden", "true");
              alert("uspjesno");
          });
          window.location.href = 'http://localhost:4200/hotels';
      };
      discardChangeServiceClick(){
          document.getElementById('changeServiceDiv').setAttribute("hidden", "true");  
      };
      //rad sa pricingom
      changePricingClick(p) {
          this.changePricing = p;
          document.getElementById('changePricingDiv').removeAttribute('hidden');
      };
      finalChangePricingClick(newPricing2){
          console.log(newPricing2);
          this.hotelService.changePricing(newPricing2, this.changePricing.id).subscribe(data=>{
              document.getElementById('changePricingDiv').setAttribute("hidden", "true");
              alert("uspjesno");
          });
          
      };
      discardChangePricingClick(){
          document.getElementById('changePricingDiv').setAttribute("hidden", "true");  
      };
      //rad sa sobama
      changeRoomClick(r){
          this.changeRoom = r;
          this.hotelService.isReserved(this.changeRoom.id).subscribe(data=>{
              this.isReserved = data;
          });
          if (this.isReserved){
              alert("Nije moguce izmeniti sobu, rezervisana je!"); 
          }else{
              document.getElementById('changeRoomDiv').removeAttribute('hidden');
          }
      };
      deleteRoomClick(r) {
          if (confirm("Da li ste sigurni da zelite da obrisete sobu?")){
              this.hotelService.isReserved(r.id).subscribe(data=>{
                  this.isReserved = data;
              });
              
              if (this.isReserved){
                  alert("Nije moguce obrisati sobu, rezervisana je!"); 
              }else{
                  this.hotelService.deleteRoom(r.id).subscribe(data=>{
                      alert("Uspjesno obrisana soba iz hotela!");
                      window.location.href = 'http://localhost:4200/hotels';
                  });
              }
  
          }else{
          }
          
      };

      finalChangeRoomClick(newRoom2) {
          
          console.log(newRoom2);
          
          this.hotelService.changeRoom(newRoom2, this.changeRoom.id).subscribe(data=>{
              document.getElementById('changeRoomDiv').setAttribute("hidden", "true");
              alert("uspjesno");
          });
          window.location.href = 'http://localhost:4200/hotels';
      };
      discardChangeRoomClick(){
          document.getElementById('changeRoomDiv').setAttribute("hidden", "true");  
      };
      
      //pretraga:
      findRooms() {
          this.vraceno = new Date(this.reservationRoom.endDate);
          this.preuzeto = new Date(this.reservationRoom.startDate);
          console.log(this.reservationRoom);
          if(this.isBlank(this.reservationRoom.startDate)){
              alert("Morate odabrati datum preuzimanja");

              }
          else if(this.isBlank(this.reservationRoom.endDate)){
              alert("Morate odabrati datum vracanja");
              }      
          else if(this.isBlank(this.reservationRoom.category)){
              alert("Morate odabrati kategoriju");
              }
          else if(this.isBlank(this.reservationRoom.numPeople)){
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
          
          else if (this.reservationRoom.category=="Jednokrevetna" && this.reservationRoom.numPeople>2
                  || this.reservationRoom.category=="Dvokrevetna" && this.reservationRoom.numPeople>4
                  || this.reservationRoom.category=="Trokrevetna" && this.reservationRoom.numPeople>6
                  || this.reservationRoom.category=="Cetverokrevetna" && this.reservationRoom.numPeople>8){
              alert("Prevelik broj ljudi za tu vrstu sobe!");
          }
          else if(this.isBlank(this.cenaOd) && this.isBlank(this.cenaDo))
          { 
              console.log("printam id leta " + this.flightReservationId);
              //provera za let
              this.hotelService.chekIfFlightIsBooked(this.reservationRoom, this.flightReservationId).subscribe(data=>{
                  console.log(data);
                  if(data==true) {
                      alert("Problemi zbog rezervacije leta. Provjerite da li je" +
                      		"datum pocetka leta prije prijave u hotel. Provjerite broj ljudi.");    
                  }
              });
              this.hotelService.searchRooms(this.reservationRoom, this.currentHotel.id,-1,-1).subscribe(data=>{
                  this.rooms = data;
                  console.log("pronadjene sobe: ");
                  console.log(data);
                  this.canBook = false;
              });
              var proba = Math.abs(this.vraceno.getTime() - this.preuzeto.getTime())
              this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24)); 
          }
          else{
              if(this.isBlank(this.cenaOd))
              {
                  this.cenaDo = this.cenaDo; 
              }else if(this.isBlank(this.cenaDo))
              {
                  this.cenaOd = this.cenaOd;
              }
              this.hotelService.chekIfFlightIsBooked(this.reservationRoom, this.flightReservationId).subscribe(data=>{
                  console.log(data);
                  if(data==true) {
                      alert("Problemi zbog rezervacije leta. Provjerite da li je" +
                            "datum pocetka leta prije prijave u hotel. Provjerite broj ljudi.");    
                  }
              });
              this.hotelService.searchRooms(this.reservationRoom, this.currentHotel.id,this.cenaOd,this.cenaDo).subscribe(data=>{
                  this.rooms = data;
                  console.log("pronadjene sobe: ");
                  console.log(data);
                  this.canBook = false;
              });
              var proba = Math.abs(this.vraceno.getTime() - this.preuzeto.getTime())
              this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24)); 
              
          }
         
          
      }
      
      isBlank(str) {
          return (!str || /^\s*$/.test(str));
        }
      serviceChanged(id :number) {
          for (var i=0; i<this.services.length; i++) {
          if (this.services[i].id == id){
              
              var value = this.idServices.get(id);
    
              if(value == true){
                this.idServices.set(id, false);
                this.reservationRoom.totalPrice =this.reservationRoom.totalPrice - this.services[i].price;
           
              }
              else{
                this.idServices.set(id, true);
                this.reservationRoom.totalPrice =this.reservationRoom.totalPrice + this.services[i].price;
              }
    
               console.log('service changed');
        
         }
       }      
      }
    bookClick(r : Room) {
      if(!this.token){
         alert("Morate biti ulogovani kako bi izvrsili proces rezervacije");
      }
      else{
          if(this.isBlank(this.flightReservationId)){
          alert("Morate odabrati let");
          }else{

          this.reservationRoom.user = this.user;
          this.reservationRoom.room = r;
          //popust na dodatne servise
          if (this.reservationRoom.totalPrice > this.discount.amount){
              this.reservationRoom.totalPrice = this.reservationRoom.totalPrice - ((this.reservationRoom.totalPrice*this.discount.percent) / 100); 
              this.popust = true;
          }
          
         // this.reservationRoom.totalPrice = this.reservationRoom.totalPrice + this.brojDana*r.price;
         //total price je vec izracunat pri pretrazi
          this.reservationRoom.totalPrice = this.reservationRoom.totalPrice + r.totalPrice;

          //popust na rezervaciju hotela
          if (this.iskoristiPoene){
              console.log("dosao u iskoristi poene");
              this.reservationRoom.totalPrice =this.reservationRoom.totalPrice - ( (this.reservationRoom.totalPrice*this.discount2.percent) / 100); 
              this.sds.usePoints(this.discount2.amount, this.user.id).subscribe(data=>{
                  console.log("Umanjio broj poena");
                  this.user.points -= this.discount2.amount;
              });
          }
          console.log(this.reservationRoom);
          console.log("rezervaciju je izvrsio: " + this.reservationRoom.user.email);

          this.hotelService.bookRoom(this.reservationRoom).subscribe(data=>{
              if (this.popust){
                  
                  if (this.iskoristiPoene){
                      alert("Uspesna rezervacija. Ostvarili ste popust "+this.discount.percent+"% na dodatne servise jer ukupan iznos prelazi: " 
                              + this.discount.amount + "Ostvarili ste dodatnih " + this.discount2.percent + "% popusta na ulozene bonus poene!");
                  }else{
                      alert("Uspesna rezervacija. Ostvarili ste popust "+this.discount.percent+"% na dodatne servise jer ukupan iznos prelazi: " 
                              + this.discount.amount);
                  }
              }else{
                  if (this.iskoristiPoene){
                      alert("Uspesna rezervacija. Ostvarili ste " + this.discount2.percent + "% popusta na ulozene bonus poene!");
                 
                  }else{
                      alert("rezervacija uspesna"); 
                  }
                  
              }
              
              window.location.href="hotels";
          });
      }
    }   
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
   };
    
    getAddress() {
        this.adresa += this.currentHotel.address.replace(/ /g, '%20');
        this.finalna += "https://maps.google.com/maps?q=" + this.adresa + "&t=&z=13&ie=UTF8&iwloc=&output=embed";
        console.log("adresse");
        console.log(this.adresa);
        console.log(this.finalna);
      };
    reportClick(){
        this.viewHotelsService.selectHotel(this.currentHotel);
        this.viewHotelsService.currentHotel.subscribe(
                currentHotel => 
                {
                    console.log("Current hotel: " +  currentHotel);
                }
            );

            this.router.navigateByUrl('/hotelReport');
    };
    
    bonusPoeniChange(){
        if (this.iskoristiPoene){
            this.iskoristiPoene = false ; 
        }
        else{
            this.iskoristiPoene = true ;
        }
        console.log("iskoristi poene je: "+this.iskoristiPoene)
    }
}
