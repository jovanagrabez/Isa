import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {FlightService} from '../../services/flight.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {SeatArrangementService} from '../../services/seat-arrangement.service';
import {AviocompanySService} from '../../services/aviocompany-s.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {


  userRole: string;
  user: any;
  flight: any;
  airline: any;
  connecting: any;

  date1: any;     // za ispise datuma u labeli
  date2: any;
  fromDest: any;
  toDest: any;

  selectedDestinations: [{}];
  departureDate: NgbDate;
  departureTime: string;
  arrivalDate: NgbDate;
  arrivalTime: string;
  constructor(private appComp: AppComponent, private currentRoute: ActivatedRoute,
              private flightService: FlightService, private SeatArrangement: SeatArrangementService,
              private airlineService: AviocompanySService, private  router : Router) {
    this.flight = {destinations: [{}], seats: [{}], seatArrangement: {seatArrangement: {seatRows: 5, seatColumns: 5}}, airlineDto: {}, fromDest: '', toDest: ''};
    this.selectedDestinations = [{}];
    this.connecting = [{address: {}}];
    this.fromDest = {address: {}};
    this.toDest = {address: {}};
    this.user = {inChargeOf: 0};
    this.airline = {destination: []};


  }




  ngOnInit() {
    this.currentRoute.params.subscribe(params => {
      const flightId = params['id'];
      this.airlineService.getCompanyByFlight(flightId).subscribe(airline => {
        this.airline = airline;
      });

      this.flightService.getFlight(flightId).subscribe(flight => {
        this.flight = flight;

        // // for (const dest of this.flight.destinations) {
        // //   if (dest.description === 'departure') {
             this.flight.fromDest = this.flight.destination[0];
           this.fromDest = this.flight.destination[0];
        //   // } else if (dest.description === 'arrival') {
             this.flight.toDest = this.flight.destination[1];
             this.toDest = this.flight.destination[1];
        //   // } else {

         for(let i = 2; i < this.flight.destination.length; i++) {
           this.selectedDestinations.push(this.flight.destination[i]);
         }
        //   //  this.connecting.push(dest.destination);
        //     // this.selectedDestinations.push(dest.destination.id);
        // //   }
        // // }
        let date = new Date(this.flight.take_off.split('T', 1));

        this.date1 = date;
        this.departureDate = new NgbDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDay());
   //     this.departureTime = date.getUTCHours().toString() + ':' + date.getUTCMinutes();

        date = new Date(this.flight.landing);
        this.date2 = date;
        this.arrivalDate = new NgbDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDay());
       // this.arrivalTime = date.getUTCHours().toString() + ':' + date.getUTCMinutes();


        this.SeatArrangement.getSeatArrangement(this.flight.seatArrangement.id).subscribe( seat => {
          this.flight.seatArrangement = seat;
        }, err => {
          console.log(err);
        });
      });
    });
  }


  saveFlight() {
    // if (this.flight.fromDest === undefined || this.flight.toDest === undefined) {
    // } else if (this.departureDate === undefined || this.departureTime === null || this.arrivalDate === undefined ||
    //   this.arrivalTime === null || this.arrivalTime === undefined || this.departureTime === undefined) {
    // } else {

      // const temp = this.flight.destinations;        // jer je bila lista veza flight-destination
      this.flight.destination = [];                // a dto prima samo listu indexa svih destinacija
      // for (const d of  temp) {
      //   this.flight.destinations.push(d.destination.id);
      // }
      // for (const dest of this.connecting) {
      //   if (dest !== undefined) {
      //     if (dest['id'] !== undefined) {
      //       this.flight.destination.push(dest['id']);
      //     }
      //   }
      // }
   //   this.flight.flightChanges = this.flight.destination.length;

      this.flight.destination.push(this.flight.fromDest);
      this.flight.destination.push(this.flight.toDest);

      let hours = '0';
      let minutes = '0';
      if (this.departureTime !== '') {
        hours = this.departureTime.split(':')[0];
        minutes = this.departureTime.split(':')[1];
        // let suffix = hours >= 12 ? 'pm' : 'am';
        // hours = hours % 12 || 12;
        // hours = hours < 10 ? '0' + hours : hours;
      }
      let x = +hours[0];      // radi pretvaranje stringa u broj
      let y = +minutes[0];
      this.flight.take_off = '2018-02-07';
        //new Date(this.departureDate.year, this.departureDate.month - 1, this.departureDate.day);

      // month - 1 jer ga racuna od jedan, a date od 0
      hours = '0';
      minutes = '0';
      if (this.arrivalTime !== '') {
        hours = this.arrivalTime.split(':')[0];
        minutes = this.arrivalTime.split(':')[1];
      }
      x = +hours[0];      // radi pretvaranje stringa u broj
      y = +minutes[0];
      this.flight.landing = '2001-04-05' ;
    //  this.flight.airlineId = this.flight.airlineDto.id;
      this.flightService.updateFlight(this.flight).subscribe(flight => {
        this.flight = flight;

        this.router.navigate(['/avioCompany']);
      });
    //}

  }

  reservation() {
    this.router.navigate(['/flights/reservation', this.flight.id]);

  }
}
