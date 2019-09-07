import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { MatDatepicker, MatDatepickerInput, MatDatepickerInputEvent } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { Hotel } from '../models/Hotel';
import { Room } from '../models/Room';
import { HotelServiceService } from '../services/hotel-service/hotel-service.service';

@Component({
  selector: 'app-hotel-report',
  templateUrl: './hotel-report.component.html',
  styleUrls: ['./hotel-report.component.css']
})
export class HotelReportComponent implements OnInit {
    
    currentHotel: Hotel = new Hotel();
    rooms : Room[];
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
    podaci: any;
    tip = 'bar';
    opcije = {
            vertikalne: false,
            responsive: true
          };
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
      
  }
  prihodiHotelaClick(){
      this.nedeljni = false;
      this.mesecni = false;
      this.dnevni = false;
      this.oceneH = false;
      this.prihodi = true;
      this.oceneS = false;
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
        let pomoc = new Date(element.start_date);
        console.log("datum pomoc " + pomoc);
        console.log("datum rez" + element.start_date);
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
      
  }
  mesecniClick(){
      
  }
  oceneHotela(){
      
  }
  oceneSoba(){
      
  }
}
