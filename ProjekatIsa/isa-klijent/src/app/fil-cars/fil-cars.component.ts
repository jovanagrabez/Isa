import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { FilijaleServiceService } from '../services/fil-service/filijale-service.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
@Component({
  selector: 'app-fil-cars',
  templateUrl: './fil-cars.component.html',
  styleUrls: ['./fil-cars.component.css']
})
export class FilCarsComponent implements OnInit {

  private currentFil : any;
  car : [];


  constructor(private router: Router, private ngZone : NgZone, private modalService: NgbModal,private filService : FilijaleServiceService) { }

  ngOnInit() {
      
      this.filService.currentFil.subscribe(
        currentFil =>
        {
            this.currentFil = currentFil;
            console.log(currentFil);
            
            this.filService.getCars(currentFil.id).subscribe(data=>{
                this.car = data;
                console.log(currentFil.id + 'usao'); 
                });
            });
  }

}
