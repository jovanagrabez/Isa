import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FlightService} from '../../../services/flight.service';
import {AviocompanySService} from '../../../services/aviocompany-s.service';

@Component({
  selector: 'app-seat',
  templateUrl: './seat.component.html',
  styleUrls: ['./seat.component.css']
})
export class SeatComponent implements OnInit {
  userRole: any;
  user: any;
  flight: any;
  airlineId: any;
  seatsInRows: any;
  selectedSeats: any;

  newSeatOpen: any;
  isUpdateActive: any;
  updateSeatsClass: any;
  isCreateTicketsActive: any;
  discountSeatPrice: any;

  newSeatRow: any;
  newSeatColumn: any;
  newSeatClass: any;
   flightId: any;
  constructor(private currentRoute: ActivatedRoute, private flightService: FlightService,
              private airlineService: AviocompanySService, private router: Router) {

    this.flight = {seatArrangement: {seatRows: 0, seatColumns: 0}, seats: [{seatColumn: 1, seatRow: 1}]};
    this.seatsInRows = [];
    this.selectedSeats = [];
    this.user = {inChargeOf: 0};
  }

  ngOnInit() {

    this.newSeatClass = 'ECONOMY';
    this.newSeatOpen = false;
    this.isCreateTicketsActive = false;
    this.isUpdateActive = false;
    this.updateSeatsClass = 'ECONOMY';
    this.discountSeatPrice = 0;

    // //   this.userRole = this.userService.getLoggedUserType();
    //    const userTemp = JSON.parse(localStorage.getItem('loggedUser'));
    //    if (userTemp !== null) {
    //      this.userService.getUserByUsername(userTemp.sub).subscribe(user => {
    //        this.user = user;
    //      });
    //    }

    this.currentRoute.parent.params.subscribe(params => {
      this.flightId = params['id'];

      this.flightService.getFlight(this.flightId).subscribe(flightTemp => {
        this.flight = flightTemp;
        //   for (let i = 0 ; i < this.flight.seats.length; i++ )
        //   this.seatsInRows.push(this.flight.seats);


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
  openNewSeat() {
    this.newSeatOpen = true;
  }
  createSeat() {
    if (this.newSeatColumn < 1  || this.newSeatRow < 1) {
      // this.toastr.errorToastr('Rows and columns have to be greater than 0', 'Error');
    } else if (this.newSeatRow <= this.seatsInRows.length ||
      this.newSeatRow === this.seatsInRows.length + 1) {// ako je postojeci red ili sledeci

      if (this.newSeatRow <= this.seatsInRows.length) {    // ako je u postojecem redu
        if (this.seatsInRows[this.newSeatRow - 1].length >= this.newSeatColumn - 1) {   // nije dobro upada 1/1 jer je duzina 2
          // ako je nova kolona
          // if (this.seatsInRows[this.newSeatRow - 1].length <= this.newSeatColumn) {
          let filled = false;
          let emptyFieldForSeat = false;
          for (const seat of this.seatsInRows[this.newSeatRow - 1]) {
            if (seat.seatColumn === this.newSeatColumn) { // kad je class undefined - nema sjedista
              if (seat.seatClass === undefined) {         // vec samo zbog iscrtavanja
                seat.seatClass = this.newSeatClass;
                emptyFieldForSeat = true;
              } else {
                filled = true;
              }
              break;
            }
          }
          if (filled) {
            // this.toastr.errorToastr('There is already a seat on that place', 'Error');
          } else if (!emptyFieldForSeat) {          // ako je bilo prazno polje, samoga promeni ne treba ga ponovo unositi
            const newSeat = {seatRow: this.newSeatRow, seatColumn: this.newSeatColumn, seatClass: this.newSeatClass, state: 'free'};
            this.seatsInRows[this.newSeatRow - 1].push(newSeat);
            const sorted = this.seatsInRows[this.newSeatRow - 1].sort((t1, t2) => {
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
        } else {
          // this.toastr.warningToastr('You are not putting seats in correct order', 'Wrong column');  // a mozda i treba?
        }
      } else {
        // novi red
        const newSeat = {seatRow: this.newSeatRow, seatColumn: this.newSeatColumn, seatClass: this.newSeatClass, state: 'free'};
        const newRow = [];
        if (this.newSeatColumn !== 1) {
          for (let i = 1; i < this.newSeatColumn; i++) {  // dodaj prazan seat, da bi novi imao dobru poziciju
            const tempSeat = {seatRow: this.newSeatRow, seatColumn: i, seatClass: undefined, state: 'free'};
            newRow.push(tempSeat);
          }
        }
        newRow.push(newSeat);
        this.seatsInRows.push(newRow);
      }
      this.newSeatOpen = false;
    } else {
      // this.toastr.warningToastr('You are not putting seats in correct order', 'Wrong row');
    }
  }

  seatSelected(seat: any) {
    if (seat.isSelected === false || seat.isSelected === undefined) {   // da li se nalazi menju selektovanim sedistima
      seat.isSelected = true;
    } else {
      seat.isSelected = false;
    }
    // for (let i = 0; i < this.selectedSeats.length; i++) {
    //   if (this.selectedSeats[i].id === seat.id) {
    //     isFound = true;
    //     this.selectedSeats.splice(i, 1);
    //     break;
    //   }
    // }
    // if (!isFound) {
    //   this.selectedSeats.push(seat);
    // }
  }

  openUpdateSeats() {
    this. isUpdateActive = true;
  }
  cancelUpdateSeats() {
    this.isUpdateActive = false;
  }

  updateSeats() {
    for (const row of this.seatsInRows) {
      for (const seat of row) {
        if (seat.isSelected === true) {
          seat.seatClass = this.updateSeatsClass;
          seat.isSelected = false;
        }
      }
    }
    this.isUpdateActive = false;
  }

  openCreateTickets() {
    this.isCreateTicketsActive = true;
  }

  cancelCreateTickets() {
    this.isCreateTicketsActive = false;
  }

  createTickets() {
    for (const row of this.seatsInRows) {
      for (const seat of row) {
        if (seat.isSelected === true) {
          seat.discountPrice = this.discountSeatPrice;
          seat.price = this.discountSeatPrice;
          seat.state = 'reserved';
          seat.isSelected = false;
        }
      }
    }
    this.isCreateTicketsActive = false;
  }

  deleteSeats() {
    for (const row of this.seatsInRows) {
      for (const seat of row) {
        if (seat.isSelected === true) {
          seat.seatClass = undefined;
          seat.isSelected = false;
        }
      }
    }
  }

  save() {
    this.flight.seats = [];
    for (const row of this.seatsInRows) {
      for (const seat of row) {
        this.flight.seats.push(seat);
      }
    }
    this.flightService.updateFlightSeats(this.flight).subscribe( flight => {
     // this.toastr.successToastr('Seats are updated succesfully', 'Success');
      this.router.navigate(['/flights', this.flightId]);
    });
  }


}
