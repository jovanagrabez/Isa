import { Component, OnInit } from '@angular/core';
import {DestinationServiceService} from '../../services/destination-service.service';
import {NgbDate, NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {error} from '@angular/compiler/src/util';
import {Router} from '@angular/router';
import {FlightService} from '../../services/flight.service';

@Component({
  selector: 'app-searchpage',
  templateUrl: './searchpage.component.html',
  styleUrls: ['./searchpage.component.css']
})
export class SearchpageComponent implements OnInit {


  from: string;
  to: string;
  flight: any;
  flights: any;

  destinations: any;
  hoveredDate: NgbDate;
  fromDate: NgbDate;
  toDate: NgbDate

  isFilterVisible: any;

  airlineNameFilter: any;
  fromDurationFilter: any;
  toDurationFilter: any;
  fromPriceFilter: any;
  toPriceFilter: any;

  constructor( private ngbDateParserFormatter: NgbDateParserFormatter, private  destinationService: DestinationServiceService, private router: Router, private flightService: FlightService) { }

  getSelected(event) {
    this.airlineNameFilter = '';
    this.fromDurationFilter = '';
    this.toDurationFilter = '';
    this.fromPriceFilter = '';
    this.toPriceFilter = '';
  }

  ngOnInit() {

    this.isFilterVisible = false;
    this.flight = { from: '', to: '', type: 1, persons: '1', seatClass: 'ECONOMY',
      toDate: '', fromDate: ''};
    this.fromDate = undefined;
    this.toDate = undefined;
    // this.destinationService.getAllDestinations().subscribe(destinations => {
    //   this.destinations = destinations;
    // });
  }


  search() {

       this.flight.fromDate = this.ngbDateParserFormatter.format(this.flight.fromDate);

        // new Date(Date.UTC(this.fromDate.year, this.fromDate.month - 1, this.fromDate.day,
      //   0, 0, 0, 0));
      this.flight.toDate = this.ngbDateParserFormatter.format(this.flight.toDate);


      this.flightService.search(this.flight).subscribe(flights => {
        this.flights = flights;
        if (this.flights.length === 0) {
          error('Nema nista sa tim parametrima');
        }
      });

  }

  flightProfile(flight) {
    this.router.navigate(['/flights', flight.id]);
  }

  onDateSelect($event) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = $event;
    } else if (this.fromDate && !this.toDate && $event.after(this.fromDate)) {
      this.toDate = $event;
    } else {
      this.toDate = null;
      this.fromDate = $event;
    }
  }

  setFilterVisible() {
    this.isFilterVisible = true;
  }

  addFilter() {

    const filter = {airline: this.airlineNameFilter, fromDuration: this.fromDurationFilter,
      toDuration: this.toDurationFilter, fromPrice: this.fromPriceFilter, toPrice: this.toPriceFilter, flights: this.flights};

    this.flightService.filterFlights(filter).subscribe( flights => {
      this.flights = flights;
      this.airlineNameFilter = '';
      this.fromDurationFilter = '';
      this.toDurationFilter = '';
      this.fromPriceFilter = '';
      this.toPriceFilter = '';
    });
    this.isFilterVisible = false;
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate &&
      this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate)
      || this.isInside(date) || this.isHovered(date);
  }


}
