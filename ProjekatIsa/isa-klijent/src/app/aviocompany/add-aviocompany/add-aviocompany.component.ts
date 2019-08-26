import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AviocompanySService} from '../../services/aviocompany-s.service';

@Component({
  selector: 'app-add-aviocompany',
  templateUrl: './add-aviocompany.component.html',
  styleUrls: ['./add-aviocompany.component.css']
})
export class AddAviocompanyComponent implements OnInit {

  airline: any;
  userRole: any;

  constructor(private router: Router, private airlineService: AviocompanySService) { }

  ngOnInit() {
    this.airline = {name: '', adress: '', description: ''};

  }

  addAviocompany(): void {
    this.airlineService.addAviocompany(this.airline).subscribe(airlineNew => {
      console.log(airlineNew);
      this.airline = airlineNew;
      this.router.navigate(['/avioCompany'], {});
    });

  }


}
