import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { ViewRentalCarsService } from '../services/view-rental-cars.service';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { SearchFormServices } from '../models/SearchFormServices';
import { SortForm } from '../models/SortForm';
import { RentACar } from '../models/RentACar';


@Component({
  selector: 'app-view-rental-cars',
  templateUrl: './view-rental-cars.component.html',
  styleUrls: ['./view-rental-cars.component.css', '../view-hotels/animate.css']
})
export class ViewRentalCarsComponent implements OnInit {
        
  private selectedRentACar: any;  
  sviServisi : RentACar[];
      
  rentalcars$: Object;
  user : User = new User();
  searchFormServices : SearchFormServices = new SearchFormServices();
  servisi : RentACar = new RentACar();
  idRez : number;
  drzava : string;
  grad : string;
  adress : string;
  sortForm : SortForm = new SortForm();


  constructor(private router : Router,private route : ActivatedRoute, private viewRentalCarsService : ViewRentalCarsService) { 
    this.adress = this.route.snapshot.params.adress;
   
      
  }

  ngOnInit() {
    
  
    
  this.user = JSON.parse(localStorage.getItem('user'));
      console.log(this.user);

      
  this.viewRentalCarsService.getRentalCars().subscribe(
    data => {
        this.sviServisi = data;
       
        }
      );
  }
    
  sortClick(){
      document.getElementById('sortDiv').removeAttribute('hidden');
      }
    
  searchClick(){
      document.getElementById('searchDiv').removeAttribute('hidden');
      }
    
   onClickCompanyDetails(RentACar:any) : void {
        this.selectedRentACar = RentACar;
        this.viewRentalCarsService.selectRentACar(RentACar);
        this.viewRentalCarsService.currentRentACar.subscribe(
          currentRentACar=>
          {
              console.log("Rent a car service" + currentRentACar);
              }
       );
        
        this.router.navigateByUrl('/rentacar-details');
    }
    
    pretraga(){
        
        console.log(this.searchFormServices.city);
        console.log(this.searchFormServices.name);
        
        this.viewRentalCarsService.searchService(this.searchFormServices).subscribe(data=>{
            this.sviServisi=data;
            console.log("pretrazeni servisi");
            console.log(data);
        });

    }
    
    
 sortServices()
   {
    console.log(this.sortForm);
    this.viewRentalCarsService.sortingService(this.sortForm, this.sviServisi).subscribe(data => {
        this.sviServisi = data as Array<RentACar>;
        console.log(this.sviServisi);
        console.log('List is sorted.');
    });
      }
    
    

//    sortName(){
//      var pomocna = this.sviServisi;
//      //pomocna.sort((a,b) => a.name.rendered.localeCompare(b.name.rendered));
//      console.log(pomocna);
//      var sortedArray : string[] = pomocna.sort((n1,n2) => {
//        if(n1.name > n2.name){
//          return 1;
//        }
//
//        if(n2.name > n1.name){
//          return -1;
//        }
//      });
//      this.sviServisi = sortedArray;
//        console.log(sortedArray);
//        }
//   
//
//
//    sortCity(){
//      var pomocna = this.sviServisi;
//      var sortedArray : string[] = pomocna.sort((n1,n2) => {
//        if(n1.adress > n2.adress){
//          return 1;
//        }
//
//        if(n2.adress > n1.adress){
//          return -1;
//        }
//      });
//      this.sviServisi = sortedArray;
//        console.log(sortedArray);
//        }
    }
