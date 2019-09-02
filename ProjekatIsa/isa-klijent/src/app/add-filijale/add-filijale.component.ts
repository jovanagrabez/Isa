import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Filijale } from '../models/Filijale';
import { FilijaleServiceService } from '../services/fil-service/filijale-service.service'
@Component({
  selector: 'app-add-filijale',
  templateUrl: './add-filijale.component.html',
  styleUrls: ['./add-filijale.component.css']
})
export class AddFilijaleComponent implements OnInit {

  fil : Filijale = new Filijale();
  hideError : boolean;
  errorMessage : string;  
  constructor(private router : Router, private filService : FilijaleServiceService) { }

  ngOnInit() {
     this.hideError = true;

  }
    
  addFilijale() {
      this.errorMessage = '';
      this.hideError = true;
 
      if (!this.fil.grad) {
        this.hideError = false;
        this.errorMessage = 'Morate uneti grad!';
      }else if (!this.fil.drzava) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti drzavu!';
      }else if (!this.fil.adresa) {
          this.hideError = false;
          this.errorMessage = 'Morate uneti adresu!';
      }
      else {
          this.filService.addFilijale(this.fil).subscribe(data=>{
              console.log("uspjesnooooo")
              alert("Uspjesno dodana filijala!")
              window.location.href = 'http://localhost:4200';
          });
          }
        }

}
