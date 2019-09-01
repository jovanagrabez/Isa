import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AviocompanySService } from '../../services/aviocompany-s.service';
import {DestinationServiceService} from '../../services/destination-service.service';


@Component({
  selector: 'app-profilcompany',
  templateUrl: './profilcompany.component.html',
  styleUrls: ['./profilcompany.component.css']
})
export class ProfilcompanyComponent implements OnInit {
        
      private currentCompany: any;
      private addDestination = false;
      private destinationNew: any;

  selectedDestinations: [{}];
  destinations: [];

  constructor(private router: Router, private data : AviocompanySService, private dest: DestinationServiceService) {

    this.destinationNew = {id: null, name: '', country: ''};
  }

  ngOnInit() {




    this.data.currentCompany.subscribe(
      currentCompany => 
      {
        this.currentCompany = currentCompany;
        console.log(currentCompany);
  }
           
    );
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
}
