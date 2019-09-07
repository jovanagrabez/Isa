import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AviocompanySService } from '../../services/aviocompany-s.service';
import {DestinationServiceService} from '../../services/destination-service.service';
import {NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {FlightService} from '../../services/flight.service';
import {NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';
import {AppComponent} from '../../app.component';
import Map from 'ol/Map';
import Tile from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import { DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-profilcompany',
  templateUrl: './profilcompany.component.html',
  styleUrls: ['./profilcompany.component.css']
})
export class ProfilcompanyComponent implements OnInit {
        
      private currentCompany: any;
      private addDestination = false;
      private destinationNew: any;
      ff: any;
      private ifflight = false;

      private destinationss: any;
  selectedDestinations: [{}];
  update = false;
  add = false;
  presjedanja: any;
//za mape
  adresa = "";
  finalna = "";
  map;
  
  constructor(private router: Router,private sanitizer:DomSanitizer, private data : AviocompanySService,
              private dest: DestinationServiceService, private flightService: FlightService,
              private ngbDateParserFormatter: NgbDateParserFormatter,
              private appCom: AppComponent) {

    this.selectedDestinations = [{id: null, name: '', country: ''}];
    this.presjedanja  = 0;
    this.destinationNew = {id: null, name: '', country: '', description: ''};
    this.ff = { id: null, take_off: NgbDate, landing: NgbDate, number: 11, seat: [{}], averageRating: 0,
      distance: 452, baggageDescription: 'hhh', businessPrice: 154, economyPrice: 562, time: 0,
      firstPrice: 1251, premiumEconomyPrice: 1524, destination: [] };

    this.destinationss = {fromDest: {id: null , name: '', country: '', description: ''}, toDest:  {id: null , name: '', country: '', description: ''}};
  }

  ngOnInit() {


  this.presjedanja = 0;

    this.data.currentCompany.subscribe(
      currentCompany => 
      {
        this.currentCompany = currentCompany;



  }
           
    );
    this.getAddress()
      }

  saveAviocompany() {
    this.data.updateAviocompany(this.currentCompany).subscribe(airlineNew => {
      console.log(this.currentCompany);

      this.router.navigate(['/avioCompany'], {});
    });
  }

  destinacije() {
    this.addDestination = true;
  }

  dodajDestinaciju() {
    this.dest.addDestination(this.destinationNew).subscribe(destinationNew => {
      this.destinationNew = destinationNew;
      this.currentCompany.destination.push(this.destinationNew);
    });


  }

  onChange($event) {
    this.selectedDestinations.push($event);

  }

  addDestinations() {

  }

  deleteDestination(destinationDelete) {
    this.dest.deleteDestination(destinationDelete.id, this.currentCompany.id).subscribe(airlineNew => {
      console.log(this.currentCompany);

      this.router.navigate(['/avioCompany'], {});
    });
  }

  letovi() {
    this.ifflight = true;
    this.add = true;
  }

  saveFlight() {
    this.ff.destination.push(this.destinationss.fromDest);
    this.ff.destination.push(this.destinationss.toDest);
    this.ff.take_off = this.ngbDateParserFormatter.format(this.ff.take_off);
  this.ff.landing = this.ngbDateParserFormatter.format(this.ff.landing);
  this.ff.seatArrangement = {seatColumns: 0, seatRows: 0};
    // if (this.selectedDestinations !== undefined) {
    //   for (const dest of this.selectedDestinations) {
    //     if (dest !== undefined) {
    //       if (dest instanceof Array) {
    //         for (const d of dest) {
    //           if (d['id'] !== undefined) {
    //             this.ff.destination.push(d);
    //           }
    //         }
    //       } else if (dest['id'] !== undefined) {
    //         this.ff.destination.push(dest);
    //       }
    //     }
    //   }
    // }



  this.flightService.addFlight(this.ff).subscribe(ff => {
     console.log(this.ff);
     this.ff = ff;
     this.ifflight = false;
     this.add = false;
     this.currentCompany.flight.push(this.ff);
    });
  }

  deleteFlight(a) {
    this.flightService.deleteFlight(a.id, this.currentCompany.id).subscribe(airlineNew => {
      console.log(this.currentCompany);

      this.router.navigate(['/avioCompany'], {});
    });


  }

  updateFlight() {
    this.flightService.addFlight(this.ff).subscribe(ff => {
      console.log(this.ff);
    });
  }

  updateF(a) {
    this.ff = a;
    this.ifflight = true;
    this.update = true;
  }


  flightProfile(flight) {
    this.router.navigate(['/flights', flight.id]);
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
  this.adresa += this.currentCompany.adress.replace(/ /g, '%20');
  this.finalna += "https://maps.google.com/maps?q=" + this.adresa + "&t=&z=13&ie=UTF8&iwloc=&output=embed";
  console.log("adresse");
  console.log(this.adresa);
  console.log(this.finalna);
}
}
