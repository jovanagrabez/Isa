import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { MatDatepicker, MatDatepickerInput, MatDatepickerInputEvent } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { Hotel } from '../models/Hotel';
import { Room } from '../models/Room';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';
import { ReservationRoom } from '../models/ReservationRoom';

@Component({
  selector: 'app-hotel-report',
  templateUrl: './hotel-report.component.html',
  styleUrls: ['./hotel-report.component.css']
})
export class HotelReportComponent implements OnInit {
    
    currentHotel: Hotel = new Hotel();
    rooms : any;
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
    allRatings : any;
    allRatingsRoom : any;
    podaci: any;
    tip = 'bar';
    opcije = {
            vertikalne: false,
            responsive: true
          };
    reservations :  any;
    brojDana : number;
  constructor(private datePipe: DatePipe, private route: ActivatedRoute, 
          private viewHotelsService:ViewHotelsService,private hotelService : HotelServiceService) { }

  ngOnInit() {
      this.pomoc = this.datePipe.transform(Date.now(), 'yyyy-MM-dd');
      console.log(this.pomoc);
      this.pomocDva = this.pomoc;
      this.minDatum = new Date(this.pomoc);
      this.viewHotelsService.currentHotel.subscribe(
              currentHotel => 
              {
                this.currentHotel = currentHotel;
                console.log(currentHotel);
                
                this.viewHotelsService.getAllRooms(this.currentHotel.id).subscribe(data=>{
                    this.rooms = data;
                    console.log(data)
                });
      });
      
      this.hotelService.getLastWeekReservations(this.currentHotel.id, this.pomoc).subscribe(data=>{
          this.prethodnaNedelja = data;
          console.log("rezervacije prosle nedelje");
          console.log(data);
          });
      this.hotelService.getAllReservations(this.currentHotel.id).subscribe(data=>{
          this.reservations = data;
          console.log(" sve rezervacija hotela "+ this.reservations.length);
          console.log(data);
      });
  }
  prihodiHotelaClick(){
      this.nedeljni = false;
      this.mesecni = false;
      this.dnevni = false;
      this.oceneH = false;
      this.prihodi = true;
      this.oceneS = false;
  }
  intervalDatuma() {
      this.pomocDva = (<HTMLInputElement>document.getElementById("datMin")).value;
     // alert("USAO " + this.pomocDva);
    }
  pretraziHotele() {
      this.od = this.datePipe.transform(this.intervalOd, "yyyy-MM-dd HH:mm:ss.S");
      this.do = this.datePipe.transform(this.intervalDo, "yyyy-MM-dd HH:mm:ss.S");
      console.log(this.od + " this od");
      this.hotelService.getHotelRevenue(this.currentHotel.id, this.od, this.do).subscribe(data => {
        this.suma = data

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
      let data = Array;
      let pon = 0;
      let uto = 0;
      let sre = 0;
      let cet = 0;
      let pet = 0;
      let sub = 0;
      let ned = 0;


      this.prethodnaNedelja.forEach(element => {
        let pomoc = new Date(element.startDate);
        console.log("datum pomoc " + pomoc);
        console.log("datum rez" + element.startDate);
        if (pomoc.getDay() == 1) {
          pon += 1;
        } else if (pomoc.getDay() == 2) {
          uto += 1;

        } else if (pomoc.getDay() == 3) {
          sre += 1;
        } else if (pomoc.getDay() == 4) {
          cet += 1;
        } else if (pomoc.getDay() == 5) {
          pet += 1;
        } else if (pomoc.getDay() == 6) {
          sub += 1;
        } else if (pomoc.getDay() == 7) {
          ned += 1;
        }


      });

      this.podaci = [
        { data: [pon, uto, sre, cet, pet, sub, ned], label: this.currentHotel.name }
      ];
      this.labele = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
    }
  nedeljniClick(){
      this.nedeljni = true;
      this.mesecni = false;
      this.dnevni = false;
      this.prihodi = false;
      this.oceneH = false;
      this.oceneS = false;
      this.popuniN();
  }
  mesecniClick(){
      this.mesecni = true;
      this.nedeljni = false;
      this.dnevni = false;
      this.prihodi = false;
      this.oceneH = false;
      this.oceneS = false;
      this.popuni(); 
  }

  popuni() {
    let data = Array;
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
      let pomoc = new Date(element.startDate);
      console.log("datum pomoc " + pomoc);
      console.log("datum rez" + element.startDate);
      let casa = new Date(element.startDate);
      casa = new Date(this.datePipe.transform(casa, "yyyy-MM-dd"));

      let casa2 = new Date(Date.now());
      casa2 = new Date(this.datePipe.transform(casa2, 'yyyy-MM-dd'));
      if (casa < casa2) {
        if (pomoc.getMonth() == 0) {
          jan += 1;
        } else if (pomoc.getMonth() == 1) {
          feb += 1;
        } else if (pomoc.getMonth() == 2) {
          mart += 1;
        } else if (pomoc.getMonth() == 3) {
          april += 1;
        } else if (pomoc.getMonth() == 4) {
          maj += 1;
        } else if (pomoc.getMonth() == 5) {
          jun += 1;
        } else if (pomoc.getMonth() == 6) {
          jul += 1;
        } else if (pomoc.getMonth() == 7) {
          console.log("av" + pomoc.getMonth());
          av += 1;
        } else if (pomoc.getMonth() == 8) {
          console.log("sept" + pomoc.getMonth());
          sep += 1;
        } else if (pomoc.getMonth() == 9) {
          okt += 1;
        } else if (pomoc.getMonth() == 10) {
          nov += 1;
        } else if (pomoc.getMonth() == 11) {
          dec += 1;
        }
      }

    });

    this.podaci = [
      { data: [jan, feb, mart, april, maj, jun, jul, av, sep, okt, nov, dec], label: this.currentHotel.name }
    ];

    this.labele = ['Januar', 'Februar', 'Mart', 'April', 'Maj', 'Jun', 'Jul', 'Avgust', 'Septembar', 'Oktobar', 'Novembar', 'Decembar'];
  }
  oceneHotela(){
      this.nedeljni = false;
      this.mesecni = false;
      this.oceneS = false;
      this.dnevni = false;
      this.oceneH = true;
      this.hotelService.getAllRatingsHotel(this.currentHotel.id).subscribe(data => {
        this.allRatings = data
      });
  }
  oceneSoba(){
      this.allRatingsRoom = [];
      let pomocna;
      this.nedeljni = false;
      this.mesecni = false;
      this.oceneS = false;
      this.dnevni = false;
      this.oceneH = false;
      this.oceneS = true;
      this.rooms.forEach(element => {
        this.hotelService.getRatingRoom(element.id).subscribe(data => {
          pomocna = data;
          if (pomocna.length != 0 && data != undefined) {
            pomocna.forEach(el => {
              //console.log(el.ocena);
              this.allRatingsRoom.push(el);
            
            });
          }

      });
    });
  }
  
  popuniN() {
      let data = Array;
      let pon = 0;
      let uto = 0;
      let sre = 0;
      let cet = 0;
      let pet = 0;
      let sub = 0;
      let ned = 0;


      this.reservations.forEach(element => {
        let pomoc = new Date(element.startDate);
        var proba = Math.abs(new Date(element.endDate).getTime() - new Date(element.startDate).getTime())
        this.brojDana =  Math.ceil(proba / (1000 * 3600 * 24)); 
        let noci = this.brojDana;
        let dan = new Array();
        console.log("datum pomoc " + pomoc);
        console.log("datum rez" + element.startDate);
        console.log("datum rez" + noci);
        let casa = new Date(element.startDate);
        casa = new Date(this.datePipe.transform(casa, "yyyy-MM-dd"));

        let casa2 = new Date(Date.now());
        casa2 = new Date(this.datePipe.transform(casa2, 'yyyy-MM-dd'));
        if (casa < casa2) {
          if (pomoc.getDay() == 1) {
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            pon += 1;
            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              console.log("sledeci " + i + " " + dan[i]);
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

            uto += 1;
            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              console.log("sledeci " + i + " " + dan[i]);
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

            sre += 1;
            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              console.log("sledeci " + i + " " + dan[i]);
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

            cet += 1;
            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              //console.log("sledeci " + i + " " +dan[i]);
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

            console.log("pet " + pet);
            console.log("sub " + sub);
            console.log("ned " + ned);

          } else if (pomoc.getDay() == 5) {
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);

            pet += 1;
            console.log("pet " + pet);

            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              console.log("sledeci " + i + " " + dan[i]);
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
            console.log("6 je " + dan[6]);

            console.log("pet " + pet);
            console.log("sub " + sub);
            console.log("ned " + ned);
            console.log("pon " + pon);
            console.log("uto " + uto);
            console.log("sre " + sre);
          } else if (pomoc.getDay() == 6) {
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);
            dan.push(0);

            sub += 1;
            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              console.log("sledeci " + i + " " + dan[i]);
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

            ned += 1;
            noci--;
            let i = 0;
            while (noci != 0) {
              dan[i] += 1;
              console.log("sledeci " + i + " " + dan[i]);
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
        { data: [pon, uto, sre, cet, pet, sub, ned], label: this.currentHotel.name }
      ];
      this.labele = ['Ponedeljak', 'Utorak', 'Sreda', 'Cetvrtak', 'Petak', 'Subota', 'Nedelja'];
    }

    }
