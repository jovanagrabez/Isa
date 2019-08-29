import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AviocompanySService } from '../services/aviocompany-s.service';

import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-avio-companies',
  templateUrl: './view-avio-companies.component.html',
  styleUrls: ['./view-avio-companies.component.css']
})
export class ViewAvioCompaniesComponent implements OnInit {

       niz$: Object ;
      private selectedCompany : any;


  currentRate:string;
   
constructor(private router: Router, private avioService : AviocompanySService) {

  
  }

    
  ngOnInit() {
      
   //  this.currentRate="bla bla bla";
      
    
         this.avioService.getAvioCompany().subscribe(
    data => this.niz$ = data
    );



  }
    
    
   onClickCompanyDetails(Aviocompany : any) :void {
    this.selectedCompany = Aviocompany; 
    this.avioService.selectAviocompany(Aviocompany);
    this.avioService.currentCompany.subscribe(
      currentCompany => 
      {
      console.log("Current company: " +  currentCompany);
      }
    );

    this.router.navigateByUrl('/profilcompany');

  }


}
