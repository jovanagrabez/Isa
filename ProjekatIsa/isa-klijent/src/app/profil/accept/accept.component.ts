import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FlightReservationService} from '../../services/flight-reservation.service';

@Component({
  selector: 'app-accept',
  templateUrl: './accept.component.html',
  styleUrls: ['./accept.component.css']
})
export class AcceptComponent implements OnInit {


  reservation: any;
  constructor(private currentRoute: ActivatedRoute, private reservationService: FlightReservationService, private router: Router) { }

  ngOnInit() {

    this.currentRoute.params.subscribe(params => {
      const resId = params['id'];
      this.reservationService.getInvitation(resId).subscribe( res => {
        this.reservation = res;
      });
    });
  }


  accept() {
    this.router.navigate(['/home']);
  }

  delete() {
    this.reservationService.deleteInvitation(this.reservation.id).subscribe( res => {
      this.router.navigate(['/home']);
    });
  }

}
