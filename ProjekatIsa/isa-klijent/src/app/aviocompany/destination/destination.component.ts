import { Component, OnInit } from '@angular/core';
import {DestinationServiceService} from '../../services/destination-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent implements OnInit {

  destinationNew: any;
  constructor(private dest: DestinationServiceService, private  router: Router) {
    this.destinationNew = {name: '', country: ''};
  }

  ngOnInit() {
  }

  addDestination() {

  }


  dodajDestinaciju() {
    this.dest.addDestination(this.destinationNew).subscribe(destinationNew => {
      this.destinationNew = destinationNew;
      this.router.navigate(['/avioCompany'], {});
    });


  }
}
