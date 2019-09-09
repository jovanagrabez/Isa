import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AviocompanySService } from '../../services/aviocompany-s.service';

import { Observable } from 'rxjs';
import {AuthServiceService} from '../../services/auth-service.service';
import { SortForm } from '../../models/SortForm';
import { AvioCompany } from '../../models/AvioCompany';

@Component({
  selector: 'app-view-avio-companies',
  templateUrl: './view-avio-companies.component.html',
  styleUrls: ['./view-avio-companies.component.css']
})
export class ViewAvioCompaniesComponent implements OnInit {

       niz$: Object ;
      private selectedCompany : any;
      sviServisi : any;



  currentRate:string;
  sortForm : SortForm = new SortForm();

   
constructor(private router: Router, private avioService: AviocompanySService , private  role: AuthServiceService) {

  
  }

    
  ngOnInit() {

  this.avioService.getAvioCompany().subscribe(
    data => {
    //this.niz$ = data;
    this.sviServisi = data;
    console.log(data);
    });
         



  }
    
      
   sortClick(){
      document.getElementById('sortDiv').removeAttribute('hidden');
      }
   
   sortServices()
   {
    console.log(this.sortForm);
    this.avioService.sortingService(this.sortForm, this.sviServisi).subscribe(data => {
        this.sviServisi = data;
        console.log(this.sviServisi);
        console.log('List is sorted.');
    });
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

    this.router.navigateByUrl('/profilcompany/' + this.selectedCompany.id);

  }

  onClickDelete(Aviocompany: any): void {
    this.avioService.deleteAviocompany(Aviocompany.id).subscribe(a => {
      this.ngOnInit();
    });

  }


}
