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

   private niz$:any ;
      private selectedCompany : any;


  currentRate:string;
   
constructor(private router: Router, private data : AviocompanySService) {

  
  }

  ngOnInit() {
      
     this.currentRate="bla bla bla";
      
    
         this.data.getAvioCompany().subscribe(
      res => {
          this.niz$ = res;
          
          console.log(this.niz$);
          }
    );



  }
    
    
   onClickCompanyDetails(Aviocompany : any) :void {
    this.selectedCompany = Aviocompany; 
    this.data.selectAviocompany(Aviocompany);
    this.data.currentCompany.subscribe(
      currentCompany => 
      {
      console.log("Current company: " +  currentCompany);
      }
    );

    this.router.navigateByUrl('/profilcompany');

  }


}
