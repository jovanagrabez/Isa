import { Component, OnInit, NgZone } from '@angular/core';

import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { User } from '../models/User';
import { Hotel } from '../models/Hotel';
import { Room } from '../models/Room';
import { AdditionalServiceForHotel } from '../models/AdditionalServiceForHotel';


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
    newService : AdditionalServiceForHotel = new AdditionalServiceForHotel();
    rooms : Room[];
    services : AdditionalServiceForHotel[];

    constructor(private router: Router, private viewHotelsService : ViewHotelsService,
            private hotelService : HotelServiceService,private ngZone : NgZone, private modalService: NgbModal) { }

    ngOnInit() {
 
        this.user = JSON.parse(localStorage.getItem('user'));

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
         }else{
             this.hotelService.addRoom(newRoom, this.currentHotel.id).subscribe(data=>{
                 alert("Uspjesno dodana soba!");
                 window.location.href = 'http://localhost:4200/hotels'; 
             });
             document.getElementById('addRoomDiv').setAttribute("hidden", "true");  
         }
      };
}
