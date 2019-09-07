import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarReservation } from '../models/CarReservation';
import { User } from '../models/User';
import { ResServiceService } from '../services/res-service/res-service.service';
import { AuthServiceService } from '../services/auth-service.service';
import { UserService } from '../services/user-service/user.service';
import { Car } from '../models/Car';
import { CarServiceService } from '../services/car-service/car-service.service';
import { RatingCar } from '../models/RatingCar';
import { RatingServiceService } from '../services/rating-service/rating-service.service';
import { ReservationRoom } from '../models/ReservationRoom';
import { Room } from '../models/Room';
import { RatingRoom } from '../models/RatingRoom';
import { RatingService } from '../models/RatingService';
import { RentACar } from '../models/RentACar';
import { RatingHotel } from '../models/RatingHotel';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {
        
  rezervisanaVozila: Array<CarReservation>;
  rezervisaniHoteli: Array<ReservationRoom>;

  user : User = new User();
  id : number;
  token: string;
  currentUser : User = new User();
  name : string;
  daniDoIsteka : number;
  ratingCarNumber : RatingCar = new RatingCar();
  serviceNumber : RatingService = new RatingService();
  ocenaSobe : RatingRoom = new RatingRoom();
  rating : number;
  hotelNumber : RatingHotel = new RatingHotel();
    
  carRatings : Array<RatingCar>;
  roomRatings : Array<RatingRoom>;
  serviceRatings : Array<RatingService>;
  today = new Date(Date.now());

  constructor(private userService: UserService,private router: Router, private resService : ResServiceService,
  private auth: AuthServiceService,private carService : CarServiceService, private ratingService : RatingServiceService) { }

  ngOnInit() {
      this.daniDoIsteka = 2;
      this.token = this.auth.getJwtToken();
      this.user = JSON.parse(localStorage.getItem('user'));
      this.user.id = this.id;
      //console.log(this.user.id);
     
      
      this.userService.getLogged(this.token).subscribe(data => {
      //this.pathToList(data);
      this.currentUser=data as User;
      console.log('Token je ');
      console.log(this.token);
      console.log(data);
      console.log('Uloga trenutnog usera');
      console.log(this.currentUser.roles);
      console.log(this.currentUser.id);
      
      
      this.resService.getUserRes(this.currentUser.id).subscribe(data=>{
          this.rezervisanaVozila = data;
          console.log(data);
          
          this.ratingService.getUserRatings(this.currentUser.id).subscribe(data=>{
              this.carRatings = data;
              
              this.rezervisanaVozila.forEach(element=>{
                  element.rateCar = true;
                  
                  var pickup = new Date(element.startDate);
                  var returnc = new Date(element.endDate);
//                  
                  var proba = (pickup.getTime() - this.today.getTime());
                  var daysLeft = Math.ceil(proba / (1000 * 3600 * 24));
                  element.daysLeft = daysLeft;
                   if(returnc.getTime() > this.today.getTime())
                    {
                        element.rateCar = false;
                    }
                  else 
                    {
                       this.carRatings.forEach(element2=>{
                           
                           if(element2.car.id == element.car.id)
                           {
                               element.rateCar = false;
                               }
                           });
                    }
                  
                    });
             
                  });
          
          this.ratingService.getServiceRatings(this.currentUser.id).subscribe(data=>{
              this.serviceRatings = data;
              
              
              this.rezervisanaVozila.forEach(element=>{
                  element.rateService = true;
                  
                  var returns = new Date(element.endDate);
                  
                  if(returns.getTime() > this.today.getTime())
                  {
                      element.rateService = true;
                      }
                  else {

                      }
                  });
              
              });
          
          ////ovdje nastavjam provjere za servise
             });
          
          
       this.resService.getUserHotelRes(this.currentUser.id).subscribe(data=>{
          this.rezervisaniHoteli = data;
          console.log(data);
           
           this.ratingService.getUserRoomRes(this.currentUser.id).subscribe(data=>{
               this.roomRatings = data;
               });
          });
          
       });
          
    }
            
    carClick(){
      document.getElementById('carDiv').removeAttribute('hidden');

        }
    
    hotelClick(){
      document.getElementById('hotelDiv').removeAttribute('hidden');

        }
    
    otkazi(c : CarReservation){
    
       this.resService.cancelReservation(c.id).subscribe(data =>{
       alert("Uspesna odjava");
       window.location.href="http://localhost:4200";
    });
  }
    
    otkaziSobu(c : ReservationRoom){
    
       this.resService.cancelRoomReservation(c.id).subscribe(data =>{
       alert("Uspesna odjava");
       window.location.href="http://localhost:4200";
    });
  }
    
    rateRoom(room : Room){
        
        this.ocenaSobe.rate = (<HTMLInputElement>document.getElementById("hotel"+room.id)).valueAsNumber;
        if(this.isBlank(this.ocenaSobe.rate))
        {
          alert("Morate odabrati ocenu");
        }else if(this.ocenaSobe.rate < 0 || this.ocenaSobe.rate > 5)
        {
          alert("Ocena mora biti u opsegu od 1 do 5");
        }
        else
        {
          this.ocenaSobe.user = this.user;
          this.ocenaSobe.room = room;
          this.ratingService.rateRoom(this.ocenaSobe).subscribe(data =>{
            alert("Ocenili ste sobu");
            window.location.href="http://localhost:4200";
          });
        }
     
    }
    
    
    
    rateCar(car : Car){
        
        this.ratingCarNumber.rate = (<HTMLInputElement>document.getElementById("car"+car.id)).valueAsNumber;
        if(this.isBlank(this.ratingCarNumber.rate))
        {
          alert("Morate odabrati ocenu");
        }else if(this.ratingCarNumber.rate < 0 || this.ratingCarNumber.rate > 5)
        {
          alert("Ocena mora biti u opsegu od 1 do 5");
        }
        else
        {
          this.ratingCarNumber.user = this.user;
          this.ratingCarNumber.car = car;
          this.ratingService.rateCar(this.ratingCarNumber).subscribe(data =>{
            alert("Ocenili ste vozilo " + car.name + " ocenom " + this.ratingCarNumber.rate + "!");
            window.location.href="http://localhost:4200";
          });
        }
     
    }
    
    
     rateService(car : Car){
        
        this.serviceNumber.rate = (<HTMLInputElement>document.getElementById("s"+car.id)).valueAsNumber;
        console.log(this.serviceNumber.rate)
        console.log(car.id);
        if(this.isBlank(this.serviceNumber.rate))
        {
          alert("Morate odabrati ocenu");
        }else if(this.serviceNumber.rate < 0 || this.serviceNumber.rate > 5)
        {
          alert("Ocena mora biti u opsegu od 1 do 5");
        }
        else
        {
          this.serviceNumber.user = this.user;
          //this.serviceNumber.service = car;
          this.ratingService.rateService(this.serviceNumber,car.id).subscribe(data =>{
            alert("Ocenili ste servis");
            window.location.href="http://localhost:4200";
          });
        }
     
    }
    
    
    rateHotel(room : Room){
        
        this.hotelNumber.rate = (<HTMLInputElement>document.getElementById("s"+room.id)).valueAsNumber;
        console.log(this.hotelNumber.rate)
        console.log(room.id);
        if(this.isBlank(this.hotelNumber.rate))
        {
          alert("Morate odabrati ocenu");
        }else if(this.hotelNumber.rate < 0 || this.hotelNumber.rate > 5)
        {
          alert("Ocena mora biti u opsegu od 1 do 5");
        }
        else
        {
          this.hotelNumber.user = this.user;
          //this.serviceNumber.service = car;
          this.ratingService.rateHotel(this.hotelNumber,room.id).subscribe(data =>{
            alert("Ocenili ste hotel");
            window.location.href="http://localhost:4200";
          });
        }
     
    }
    
    
    
    isBlank(str) {
    return (!str || /^\s*$/.test(str));
}
    
    

}
