import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FlightService} from '../../../services/flight.service';
import {FriendsService} from '../../../services/friends.service';
import {UserService} from '../../../services/user-service/user.service';
import {AppComponent} from '../../../app.component';
import {error} from '@angular/compiler/src/util';
import {FlightReservationService} from '../../../services/flight-reservation.service';
import {SearchFormServices} from '../../../models/SearchFormServices';
import {ViewRentalCarsService} from '../../../services/view-rental-cars.service';
import {Discount} from '../../../models/Discount';
import {DiscountHotel} from '../../../models/DiscountHotel';
import {ReservationRoom} from '../../../models/ReservationRoom';
import {CarReservation} from '../../../models/CarReservation';
import {ResServiceService} from '../../../services/res-service/res-service.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {DiscountServiceService} from '../../../services/discount-service/discount-service.service';
import { HotelServiceService } from '../../../services/hotel-service/hotel-service.service';

@Component({
  selector: 'app-flight-reservation',
  templateUrl: './flight-reservation.component.html',
  styleUrls: ['./flight-reservation.component.css']
})
export class FlightReservationComponent implements OnInit {

  selectedSeats: any;
  flight: any;
  seatsInRows: any;
  user: any;
  friends: any;
  invitedFriends: any;
  flightReservation: any;
  reservation: any;
  reservationHotel: any;
  discount : any;
  discounts : Array<Discount>;
  private searchFormServices: SearchFormServices = new SearchFormServices();

  discounts2 : Array<DiscountHotel>;
  // constructor(,private currentRoute: ActivatedRoute, private flightService: FlightService,
  // searchFormServices : SearchFormServices = new SearchFormServices();
  // discount : Array<Discount>;

  constructor(private sds: DiscountServiceService,private currentRoute: ActivatedRoute, private flightService: FlightService,
              private friendsService: FriendsService,
              private hotelService: HotelServiceService, private userService: UserService,
              private rentalCarsService: ViewRentalCarsService, private resServise: ResServiceService,
              private  appComp: AppComponent, private  router: Router, private reservationService: FlightReservationService) {
    this.flight = {seatArrangement: {seatRows: 0, seatColumns: 0}, seats: []};
    this.seatsInRows = [];
    this.selectedSeats = [];
    this.friends = [];

    this.invitedFriends = [];
    this.user = {firstName: '', lastName: '', password: '', email: '', phoneNumber: '', city: ''};

    this.reservation = {userId: '', flightReservation: {}, carReservation: {}, roomReservation: {}};
    this.flightReservation = {flightId: '', userId: '', passengersOnSeats: []};


  }

  ngOnInit() {

      //za dodavanje dodatnih poena
      this.sds.getDiscount(2).subscribe(data=>{
          this.discount = data;
          console.log(this.discount);
      });
    this.userService.getLogged(this.appComp.token).subscribe(data => {
      this.user = data


      this.friendsService.getFriendsByUser(this.user.username).subscribe(allFriendshipsList => {
        let list;
        list = allFriendshipsList;
        for (const f of list) {
          if (f.accepted === true) {
            if (f.user2.username === this.user.username) {    // ako je trenutni user1, dodaj user2 u prijatelje ako je prihvaceno
              this.friends.push(f.user1);
            } else {                 // ako je trenutni user1 dodaj user2 u prijatelje ako je prihvaceno
              this.friends.push(f.user2);
            }
          }
        }
      });
    });


    this.currentRoute.params.subscribe(params => {
      const flightId = params['id'];
      this.flightReservation.flightId = flightId;
      this.flightService.getFlight(flightId).subscribe(flight => {
        this.flight = flight;

        let date = new Date(this.flight.landing);
        this.searchFormServices.startDate = date;
        this.searchFormServices.city = this.flight.destination[0].city;

        let maxRowNumber = 0;
        for (const seat of this.flight.seats) {
          if (seat.seatRow > maxRowNumber) {
            maxRowNumber = seat.seatRow;
          }
        }
        for (let i = 0; i < maxRowNumber; i++) {
          const row = [];
          this.seatsInRows.push(row);
        }
        // for (let i = 0; i < this.flight.seatArrangement.seatRows; i++) {     //ne moze tako jer nece pratiti raspored aviona
        //   const row = [];                                                    // admin moze da mijenja sjedista, brise dodaje
        //   this.seatsInRows.push(row);
        // }
        for (const seat of this.flight.seats) {
          const index = seat.seatRow - 1;
          this.seatsInRows[index].push(seat);
        }
        for (let i = 0; i < this.seatsInRows.length; i++) {         // sortiranje po kolonama
          const sorted = this.seatsInRows[i].sort((t1, t2) => {
            const name1 = t1.seatColumn;
            const name2 = t2.seatColumn;
            if (name1 > name2) {
              return 1;
            }
            if (name1 < name2) {
              return -1;
            }
            return 0;
          });
        }
      });
    });
  }

  reserveCar(id: number){
    const startDate = this.searchFormServices.startDate;
 //   console.log(this.rez.startDate.getTime);
    const endDate = this.searchFormServices.endDate;
    console.log('rezervacija je uspjesno izvrsena');
    this.resServise.fastReservations(-1, id , startDate, endDate, this.user.id).subscribe(data =>{
      this.reservation.carReservation = data;
      alert('Uspjesno ste rezervisali vozilo');

      //    this.router.navigateByUrl('/rentalCars');
    });
  }
  reserveRoom(id: number){
      const startDate = this.searchFormServices.startDate;
     // console.log(this.rez.startDate.getTime);
      const endDate = this.searchFormServices.endDate;
      //console.log('rezervacija je uspjesno izvrsena');
      this.resServise.fastReservationsHotel(-1, id , startDate, endDate,this.user.id).subscribe(data =>{
          alert("Uspjesno rezervisan hotel!");
          //this.router.navigateByUrl('/home');
        });
  }


  pretraga(){

    console.log(this.searchFormServices.city);
    console.log(this.searchFormServices.name);
    console.log(this.searchFormServices.nameHotel);
    this.hotelService.searchDiscountRooms(this.searchFormServices).subscribe(data=>{
        console.log('pretrazeni hoteli');
        this.discounts2=data;
        console.log(data);
    });

    this.rentalCarsService.searchServiceFast(this.searchFormServices).subscribe(data=>{
      this.discounts = data;
      console.log('pretrazeni servisi');
      console.log(data);
    });

  }


  seatSelected(seat: any) {
    let isFound = false;                            // da li se nalazi medju selektovanim sjedistima
    for (let i = 0; i < this.selectedSeats.length; i++) {
      if (this.selectedSeats[i].id === seat.id) {
        isFound = true;

        let numOfSeatsWithNOPersons = 0;
        let currentSeatHasPerson = true;
        const seatsWithoutPassengersIds = [];
        const seatsWithPassengersIds = [];
        let currentSeatIdINReservation = -1;

        for (let j = 0; j < this.flightReservation.passengersOnSeats.length; j++) {
          if (this.flightReservation.passengersOnSeats[j].passengerId === 0 ||
            this.flightReservation.passengersOnSeats[j].passengerId === this.user.id) {
            numOfSeatsWithNOPersons++;                                                // broj sjedista bez putnika
            seatsWithoutPassengersIds.push(j);
            if (this.flightReservation.passengersOnSeats[j].seat.id === seat.id) {    // da li je za trenutno sjediste dodata osoba
              currentSeatHasPerson = false;
              currentSeatIdINReservation = j;                              //  cuva id sjedista koje se brise, da ne prolazim opt kroz for
            }
          } else {
            seatsWithPassengersIds.push(j);
            if (this.flightReservation.passengersOnSeats[j].seat.id === seat.id) {
              currentSeatIdINReservation = j;
            }
          }
        }
        if (numOfSeatsWithNOPersons > 1 || currentSeatHasPerson) {         // ako ima vise slobodnih, i na trenutnom je
                                                                           // neka osoba obrisi to sjediste
          if (currentSeatHasPerson) {     // prvo vratim osobu u friends i izbacim iz invites
            for (let k = 0; k < this.invitedFriends.length; k++) {
              if (this.invitedFriends[k].id === this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerId) {
                const tempFriend = this.invitedFriends[k];
                this.friends.push(tempFriend);
                this.invitedFriends.splice(k, 1);
                // this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);

                break;
              }
            }
          } else {            // ako je broj praznih veci od 1
            if (this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerId === this.user.id) {
              // ako se brise mjesto user-a koji pravi rezervaciju
              for (const id of seatsWithoutPassengersIds) {    // uzima id od svakog mjesta bez invite-a
                if (id !== currentSeatIdINReservation) {
                  this.flightReservation.passengersOnSeats[id].passengerId = this.user.id;
                  this.flightReservation.passengersOnSeats[id].passengerName = this.user.name;

                  this.flightReservation.passengersOnSeats[id].passengerLastName = this.user.lastName;

                  this.flightReservation.passengersOnSeats[id].passengerPassport =
                    this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerPassport;
                }
              }
            }
          }
          this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);   // obrise i rezervaciju

        } else if (numOfSeatsWithNOPersons <= 1) {      // ako se nalazi samo jedno slobodno (za ovog koji rezervise)
          if (seatsWithPassengersIds.length > 0) {      // a ako ima friend invitation-a, brise se invitation umesto tog slobodnog
            const oldInvited = this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerId;
            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerId = this.user.id;
            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerName =
              this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerName;

            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerLastName =
              this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerLastName;

            this.flightReservation.passengersOnSeats[seatsWithPassengersIds[0]].passengerPassport =
              this.flightReservation.passengersOnSeats[currentSeatIdINReservation].passengerPassport;
            // premjesta podatke putnika bez id-ja(ovog koji rezervise), a brise pod inviteovanog


            for (let k = 0; k < this.invitedFriends.length; k++) {
              if (this.invitedFriends[k].id === oldInvited) {
                const tempFriend = this.invitedFriends[k];
                this.friends.push(tempFriend);
                this.invitedFriends.splice(k, 1);
                // this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);

              }
            }
          }
          this.flightReservation.passengersOnSeats.splice(currentSeatIdINReservation, 1);   // obrise i rezervaciju

        }

        this.selectedSeats.splice(i, 1);        // ako se nalazi(znaci da je sad unchecked, pa ga brise iz selektovanih
        break;
      }
    }
    if (!isFound) {
      let oneReservedSeat;
      if (this.selectedSeats.length === 0) {
        oneReservedSeat = {
          seat: seat, passengerName: this.user.firstName, passengerLastName: this.user.lastName,
          passengerPassport: '', passengerId: this.user.id
        };
      } else {
        oneReservedSeat = {seat: seat, passengerName: '', passengerLastName: '', passengerPassport: '', passengerId: 0};
      }
      this.selectedSeats.push(seat);


      this.flightReservation.passengersOnSeats.push(oneReservedSeat);
    }
  }

  reserve() {
    // this.flightReservation.passengersOnSeats = 1;

    this.reservation.flightReservation = this.flightReservation;
    if (this.flightReservation.passengersOnSeats.length > 0) {
      let isValid = true;
      for (const pass of this.flightReservation.passengersOnSeats) {
        if (pass.passengerName === '' || pass.passengerLastName === '' || pass.passengerPassport === '') {
          isValid = false;
        }
      }
      if (!isValid) {
      } else {
        this.flightReservation.userId = this.user.id;
        this.sds.addPoints(this.discount.percent, this.user.id).subscribe(data=>{
            console.log("uspjesno dodani poeni!" );
        });
        this.reservationService.createReservation(this.flightReservation).subscribe(res => {
       //   const reserv = res['id'];
          this.reservationService.sendCreatedReservationEmail(1).subscribe( val => {
            console.log('Email sent');
            this.router.navigate(['/home']);

          });
       //   const reserv = res['id'];
        });
      }
    } else {
       error('Nije rezervisano sjediste');
    }



  }



  inviteFriend(friend: any, index: number) {
    this.invitedFriends.push(friend);
    this.friends.splice(index, 1);
    for (let i = 0; i < this.flightReservation.passengersOnSeats.length; i++) {
      if (this.flightReservation.passengersOnSeats[i].passengerId === 0) {
        this.flightReservation.passengersOnSeats[i].passengerId = friend.id;
        this.flightReservation.passengersOnSeats[i].passengerName = friend.firstName;
        this.flightReservation.passengersOnSeats[i].passengerLastName = friend.lastName;
        break;
      }
    }
  }



}
