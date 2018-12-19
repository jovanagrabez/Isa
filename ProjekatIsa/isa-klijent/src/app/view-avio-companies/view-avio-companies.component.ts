import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AviocompanySService } from '../services/aviocompany-s.service';



@Component({
  selector: 'app-view-avio-companies',
  templateUrl: './view-avio-companies.component.html',
  styleUrls: ['./view-avio-companies.component.css']
})
export class ViewAvioCompaniesComponent implements OnInit {

   private avioArray: any;
    
   private data: any[] = [
    {id: 1, name: 'Hydrogen', age: 23},
    {id: 2, name: 'Helium', age: 30},
    {id: 3, name: 'Lithium', age: 2},
    {id: 4, name: 'Beryllium', age: 10},
    {id: 5, name: 'Boron', age: 15}]
  private selectedCinema : any;

 

  private latitudes : any[] = [];
  private longitudes : any[] = []; 

  public currentRate="fdfdsfdsf";
   
constructor(private router : Router, private avioService : AviocompanySService) {

  
  }

  ngOnInit() {
      
      this.currentRate="fdfdsds";
      
     this.avioService.getAvioCompany()
      .subscribe(
        data=> 
        {
          this.avioArray = data;
          
          console.log(this.avioArray);

         }
          );



  }

}
