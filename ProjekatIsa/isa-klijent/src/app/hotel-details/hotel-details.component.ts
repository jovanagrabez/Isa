import { Component, OnInit, NgZone } from '@angular/core';

import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';
import { AuthServiceService } from '../services/auth-service.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from '../models/User';
import { Hotel } from '../models/Hotel';
import { Room } from '../models/Room';
import { AdditionalServiceForHotel } from '../models/AdditionalServiceForHotel';
import { ReservationRoom } from '../models/ReservationRoom';


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
    

    constructor(private router: Router,private auth: AuthServiceService, private viewHotelsService : ViewHotelsService,
            private hotelService : HotelServiceService,private ngZone : NgZone, private modalService: NgbModal) { }

    ngOnInit() {
        this.reservationRoom.totalPrice = 0;
        this.user = JSON.parse(localStorage.getItem('user'));
        this.token = this.auth.getJwtToken();
        
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
      	 
          
          });
        
       
    };
    
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
      
      //rad sa sobama
      changeRoomClick(r){
          this.changeRoom = r;
          document.getElementById('changeRoomDiv').removeAttribute('hidden');

      };
      deleteRoomClick(r) {
          if (confirm("Da li ste sigurni da zelite da obrisete sobu?")){
              this.hotelService.deleteRoom(r.id).subscribe(data=>{
                  alert("Uspjesno obrisana soba iz hotela!");
                  window.location.href = 'http://localhost:4200/hotels';
              });
              
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
          else if (this.reservationRoom.category=="Jednokrevetna" && this.reservationRoom.numPeople>2
                  || this.reservationRoom.category=="Dvokrevetna" && this.reservationRoom.numPeople>4
                  || this.reservationRoom.category=="Trokrevetna" && this.reservationRoom.numPeople>6
                  || this.reservationRoom.category=="Cetverokrevetna" && this.reservationRoom.numPeople>8){
              alert("Prevelik broj ljudi za tu vrstu sobe!");
          }
          else if(this.isBlank(this.cenaOd) && this.isBlank(this.cenaDo))
          { 
              this.hotelService.searchRooms(this.reservationRoom, this.currentHotel.id,-1,-1).subscribe(data=>{
                  this.rooms = data;
                  console.log("pronadjene sobe: ");
                  console.log(data);
                  
              });
          }
          else{
              if(this.isBlank(this.cenaOd))
              {
                  this.cenaDo = this.cenaDo; 
              }else if(this.isBlank(this.cenaDo))
              {
                  this.cenaOd = this.cenaOd;
              }
              this.hotelService.searchRooms(this.reservationRoom, this.currentHotel.id,this.cenaOd,this.cenaDo).subscribe(data=>{
                  this.rooms = data;
                  console.log("pronadjene sobe: ");
                  console.log(data);
                  
              });
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
          this.reservationRoom.user = this.user;
          this.reservationRoom.room = r;
          console.log("rezervaciju je izvrsio: " + this.reservationRoom.user.email);
          this.hotelService.bookRoom(this.reservationRoom).subscribe(data=>{
              alert("rezervacija uspesna");
              window.location.href="hotels/";
          });
      }
          
    }
}
