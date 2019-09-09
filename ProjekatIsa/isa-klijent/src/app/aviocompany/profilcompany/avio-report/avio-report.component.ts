import { Component, OnInit } from '@angular/core';
import {DatePipe} from '@angular/common';
import {ActivatedRoute} from '@angular/router';
import {AviocompanySService} from '../../../services/aviocompany-s.service';
import {ViewAvioCompaniesComponent} from '../../view-avio-companies/view-avio-companies.component';
import {FlightService} from '../../../services/flight.service';

@Component({
  selector: 'app-avio-report',
  templateUrl: './avio-report.component.html',
  styleUrls: ['./avio-report.component.css']
})
export class AvioReportComponent implements OnInit {




  airline: any;
  flights: any;
  minDatum: Date;
  pomoc: string;
  pomocDva: string;
  intervalOd: Date;
  intervalDo: Date;
  od: string;
  do: string;
  suma: any;
  labele: any;
  mesecni = false;
  nedeljni = false;
  dnevni = false;
  oceneH = false;
  oceneS = false;
  prihodi = false;
  prethodnaNedelja: any;
  allRatings: any;
  allRatingsRoom: any;
  podaci: any;
  tip = 'bar';
  opcije = {
    vertikalne: false,
    responsive: true
  };
  reservations:  any;
  brojDana: number;

  constructor(private datePipe: DatePipe, private route: ActivatedRoute,
             private airlineService: AviocompanySService, private flightService: FlightService) {

    this.airline = {id: 0, name: '', adress: '', description: '', flight: []};
    let data = [0, 0, 0, 0, 0, 0, 0];


}



ngOnInit() {


  this.pomoc = this.datePipe.transform(Date.now(), 'yyyy-MM-dd');
  console.log(this.pomoc);
  this.pomocDva = this.pomoc;
  this.minDatum = new Date(this.pomoc);

  this.route.params.subscribe(params => {
    const avioId = params['id'];
    this.airlineService.getCompany(avioId).subscribe(airline => {
      this.airline = airline;
      this.flights = this.airline.flight;


    this.airlineService.getLastWeekReservations(this.airline.id, this.pomoc).subscribe(data => {
      this.prethodnaNedelja = data;
      console.log('rezervacije prosle nedelje');
      console.log(data);
    });
    this.airlineService.getAllReservations(this.airline.id).subscribe(data => {
      this.reservations = data;
      console.log(' sve rezervacija aviokompanije ' + this.reservations.length);
      console.log(data);
    });
  });
  });

}


  prihodiKompanijeClick() {
    this.nedeljni = false;
    this.mesecni = false;
    this.dnevni = false;
    this.oceneH = false;
    this.prihodi = true;
    this.oceneS = false;
  }
  intervalDatuma() {
    this.pomocDva = (<HTMLInputElement>document.getElementById('datMin')).value;
    // alert("USAO " + this.pomocDva);
  }
  pretraziAviokompanije() {
    this.od = this.datePipe.transform(this.intervalOd, 'yyyy-MM-dd HH:mm:ss.S');
    this.do = this.datePipe.transform(this.intervalDo, 'yyyy-MM-dd HH:mm:ss.S');
    console.log(this.od + ' this od');
    this.airlineService.getAvioRevenue(this.airline.id, this.od, this.do).subscribe(data => {
      this.suma = data;

    });
  }

  dnevniClick() {
    this.nedeljni = false;
    this.mesecni = false;
    this.dnevni = true;
    this.prihodi = false;
    this.oceneH = false;
    this.oceneS = false;
    this.popuniD();
  }
  popuniD() {
    const data = Array;
    let pon = 0;
    let uto = 0;
    let sre = 0;
    let cet = 0;
    let pet = 0;
    let sub = 0;
    let ned = 0;


    this.prethodnaNedelja.forEach(element => {

      const pomoc = new Date(element.datum);
      console.log('datum pomoc ' + pomoc);
      console.log('datum rez' + element.datum);
      if (pomoc.getDay() == 1) {
        pon += element.passengersOnSeats.length;
      } else if (pomoc.getDay() == 2) {
        uto += element.passengersOnSeats.length;

      } else if (pomoc.getDay() == 3) {
        sre += element.passengersOnSeats.length;
      } else if (pomoc.getDay() == 4) {
        cet += element.passengersOnSeats.length;
      } else if (pomoc.getDay() == 5) {
        pet += element.passengersOnSeats.length;
      } else if (pomoc.getDay() == 6) {
        sub += element.passengersOnSeats.length;
      } else if (pomoc.getDay() == 7) {
        ned += element.passengersOnSeats.length;
      }


    });

    this.podaci = [
      { data: [pon, uto, sre, cet, pet, sub, ned], label: this.airline.name }
    ];
    this.labele = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
  }
  nedeljniClick() {
    this.nedeljni = true;
    this.mesecni = false;
    this.dnevni = false;
    this.prihodi = false;
    this.oceneH = false;
    this.oceneS = false;
    this.popuniN();
  }
  mesecniClick() {
    this.mesecni = true;
    this.nedeljni = false;
    this.dnevni = false;
    this.prihodi = false;
    this.oceneH = false;
    this.oceneS = false;
    this.popuni();
  }

  popuni() {
    const data = Array;
    let jan = 0;
    let feb = 0;
    let mart = 0;
    let april = 0;
    let maj = 0;
    let jun = 0;
    let jul = 0;
    let av = 0;
    let sep = 0;
    let okt = 0;
    let nov = 0;
    let dec = 0;

    this.reservations.forEach(element => {
      const pomoc = new Date(element.datum);
      console.log('datum pomoc ' + pomoc);
      console.log('datum rez' + element.datum);
      let casa = new Date(element.datum);
      casa = new Date(this.datePipe.transform(casa, 'yyyy-MM-dd'));

      let casa2 = new Date(Date.now());
      casa2 = new Date(this.datePipe.transform(casa2, 'yyyy-MM-dd'));
      if (casa <= casa2) {
        if (pomoc.getMonth() == 0) {
          jan += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 1) {
          feb += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 2) {
          mart += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 3) {
          april += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 4) {
          maj += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 5) {
          jun += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 6) {
          jul += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 7) {
          console.log('av' + pomoc.getMonth());
          av += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 8) {
          console.log('sept' + pomoc.getMonth());
          sep += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 9) {
          okt += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 10) {
          nov += element.passengersOnSeats.length;
        } else if (pomoc.getMonth() == 11) {
          dec += element.passengersOnSeats.length;
        }
      }

    });

    this.podaci = [
      { data: [jan, feb, mart, april, maj, jun, jul, av, sep, okt, nov, dec], label: this.airline.name }
    ];

    this.labele = ['Januar', 'Februar', 'Mart', 'April', 'Maj', 'Jun', 'Jul', 'Avgust', 'Septembar', 'Oktobar', 'Novembar', 'Decembar'];
  }
  oceneKompanije() {
    this.nedeljni = false;
    this.mesecni = false;
    this.oceneS = false;
    this.dnevni = false;
    this.oceneH = true;
    this.airlineService.getAllRatingsCompany(this.airline.id).subscribe(data => {
      this.allRatings = data;
    });
  }
  oceneLetova() {
    this.allRatingsRoom = [];
    let pomocna;
    this.nedeljni = false;
    this.mesecni = false;
    this.oceneS = false;
    this.dnevni = false;
    this.oceneH = false;
    this.oceneS = true;
    this.flights.forEach(element => {
      this.airlineService.getRatingFlight(element.id).subscribe(data => {
        pomocna = data;
        if (pomocna.length != 0 && data != undefined) {
          pomocna.forEach(el => {
            // console.log(el.ocena);
            this.allRatingsRoom.push(el);

          });
        }

      });
    });
  }

  popuniN() {
    const data = Array;
    let pon = 0;
    let uto = 0;
    let sre = 0;
    let cet = 0;
    let pet = 0;
    let sub = 0;
    let ned = 0;


    this.reservations.forEach(element => {
      const pomoc = new Date(element.datum);
      const odjava = new Date(pomoc.getFullYear(), pomoc.getMonth(), pomoc.getDate() + 8);
      const proba = Math.abs(new Date(odjava).getTime() - new Date(element.datum).getTime());
      this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24));
      let noci = this.brojDana;
      const dan = new Array();

      let casa = new Date(element.datum);
      casa = new Date(this.datePipe.transform(casa, 'yyyy-MM-dd'));

      let casa2 = new Date(Date.now());
      casa2 = new Date(this.datePipe.transform(casa2, 'yyyy-MM-dd'));
      if (casa <= casa2) {
        if (pomoc.getDay() == 1) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          pon += element.passengersOnSeats.length - 1;
          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            noci--;
            i++;
          }

          uto += dan[0];
          sre += dan[1];
          cet += dan[2];
          pet += dan[3];
          sub += dan[4];
          ned += dan[5];
          pon += dan[6];

        } else if (pomoc.getDay() == 2) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);

          uto += element.passengersOnSeats.length - 1;
          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            noci--;
            i++;
          }

          sre += dan[0];
          cet += dan[1];
          pet += dan[2];
          sub += dan[3];
          ned += dan[4];
          pon += dan[5];
          uto += dan[6];

        } else if (pomoc.getDay() == 3) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);

          sre += element.passengersOnSeats.length - 1;
          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            noci--;
            i++;
          }

          cet += dan[0];
          pet += dan[1];
          sub += dan[2];
          ned += dan[3];
          pon += dan[4];
          uto += dan[5];
          sre += dan[6];

        } else if (pomoc.getDay() == 4) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);

          cet += element.passengersOnSeats.length - 1;
          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            // console.log("sledeci " + i + " " +dan[i]);
            noci--;
            i++;
          }

          pet += dan[0];
          sub += dan[1];
          ned += dan[2];
          pon += dan[3];
          uto += dan[4];
          sre += dan[5];
          cet += dan[6];



        } else if (pomoc.getDay() == 5) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);

          pet += element.passengersOnSeats.length - 1;

          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            noci--;
            i++;
          }

          sub += dan[0];
          ned += dan[1];
          pon += dan[2];
          uto += dan[3];
          sre += dan[4];
          cet += dan[5];
          pet += dan[6];

        } else if (pomoc.getDay() == 6) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);

          sub += element.passengersOnSeats.length - 1;
          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            noci--;
            i++;
          }

          ned += dan[0];
          pon += dan[1];
          uto += dan[2];
          sre += dan[3];
          cet += dan[4];
          pet += dan[5];
          sub += dan[6];

        } else if (pomoc.getDay() == 7) {
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);
          dan.push(0);

          ned += element.passengersOnSeats.length - 1;
          noci--;
          let i = 0;
          while (noci != 0) {
            dan[i] += 1;
            noci--;
            i++;
          }

          pon += dan[0];
          uto += dan[1];
          sre += dan[2];
          cet += dan[3];
          pet += dan[4];
          sub += dan[5];
          ned += dan[6];
        }

      }





    });

    this.podaci = [
      { data: [pon, uto, sre, cet, pet, sub, ned], label: this.airline.name }
    ];
    this.labele = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
  }





}

