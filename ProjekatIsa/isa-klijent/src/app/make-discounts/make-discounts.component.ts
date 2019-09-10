import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SystemDiscount } from '../models/SystemDiscount';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { DiscountServiceService } from '../services/discount-service/discount-service.service';

@Component({
  selector: 'app-make-discounts',
  templateUrl: './make-discounts.component.html',
  styleUrls: ['./make-discounts.component.css']
})
export class MakeDiscountsComponent implements OnInit {

  discount : SystemDiscount = new SystemDiscount();
  newDiscount : SystemDiscount = new SystemDiscount();
  discount2 : SystemDiscount = new SystemDiscount();
  newDiscount2 : SystemDiscount = new SystemDiscount();
  discount3 : SystemDiscount = new SystemDiscount();
  newDiscount3 : SystemDiscount = new SystemDiscount();

    constructor(private router: Router, private sds : DiscountServiceService) { }

  ngOnInit() {
      
      //popust sa id = 1 je za dodatne servise
      this.sds.getDiscount(1).subscribe(data=>{
          this.discount = data;
      });
    //popust sa id = 2 je za bonus poene
      this.sds.getDiscount(2).subscribe(data=>{
          this.discount2 = data;
      });
    //popust sa id = 3 je za hotele
      this.sds.getDiscount(3).subscribe(data=>{
          this.discount3 = data;
      });
  
  }
  changeDiscount (newDiscount){
      this.newDiscount = newDiscount;
      console.log(this.newDiscount);
      if(this.isBlank(this.newDiscount.amount)){
          alert("Morate uneti iznos!");
      }
      else if(this.isBlank(this.newDiscount.percent)){
          alert("Morate uneti procenat!");
      }      
      else{
          this.sds.changeDiscount(this.newDiscount, 1).subscribe(data=>{
              alert("Uspjesno promjenjen nacin odredjivanja popusta!");
              this.router.navigateByUrl('/make-discounts');
          });
      } 
    }
  
  changeDiscount2 (newDiscount2){
      this.newDiscount2 = newDiscount2;
      console.log(this.newDiscount2);
      if(this.isBlank(this.newDiscount2.amount)){
          alert("Morate uneti iznos!");
      }
      else if(this.isBlank(this.newDiscount2.percent)){
          alert("Morate uneti broj poena!");
      }      
      else{
          this.sds.changeDiscount(this.newDiscount2, 2).subscribe(data=>{
              alert("Uspjesno promjenjen nacin odredjivanja popusta!");
              this.router.navigateByUrl('/make-discounts');
          });
      } 
    }
  changeDiscount3 (newDiscount3){
      this.newDiscount3 = newDiscount3;
      console.log(this.newDiscount3);
      if(this.isBlank(this.newDiscount3.amount)){
          alert("Morate uneti broj poena!");
      }
      else if(this.isBlank(this.newDiscount3.percent)){
          alert("Morate unetetu procenat!");
      }      
      else{
          this.sds.changeDiscount(this.newDiscount3, 3).subscribe(data=>{
              alert("Uspjesno promjenjen nacin odredjivanja popusta!");
              this.router.navigateByUrl('/make-discounts');
          });
      } 
    }
  isBlank(str) {
      return (!str || /^\s*$/.test(str));
  }
}
