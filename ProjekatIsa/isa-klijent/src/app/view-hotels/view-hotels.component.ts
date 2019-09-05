import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SearchFormHotel } from '../models/SearchFormHotel';
import { Hotel } from '../models/Hotel';


@Component({
  selector: 'app-view-hotels',
  templateUrl: './view-hotels.component.html',
  styleUrls: ['./view-hotels.component.css', '../aviocompany/view-avio-companies/animate.css']
})
export class ViewHotelsComponent implements OnInit {

  private hotelsArray: any;
  private selectedHotel: any;
  
  private loggedInUser: any;
  private isAdmin: any;
  
  private currentRate = 2.5;
  hotels : Hotel[];
  searchFormHotel : SearchFormHotel = new SearchFormHotel();
  constructor(private router : Router, private viewHotelsService : ViewHotelsService,private http: HttpClient) { }

  ngOnInit() {
  
      this.viewHotelsService.getHotels().subscribe(
      	data => this.hotels = data
      );
      console.log("hoteli");
      console.log(this.hotels);
      
  }
	onClickShowDetails(Hotel:any) : void {
		this.selectedHotel = Hotel;
		console.log("Hotel: " + Hotel);  
    	this.viewHotelsService.selectHotel(Hotel);
		this.viewHotelsService.currentHotel.subscribe(
     		currentHotel => 
	     	{
	      		console.log("Current hotel: " +  currentHotel);
	      	}
    	);

    	this.router.navigateByUrl('/hotel-details');
	}
	
	findHotels(){
	    console.log("CITY" + this.searchFormHotel.city);
	    console.log(this.searchFormHotel.name + "nazivvvvv");

	    this.viewHotelsService.searchHotels(this.searchFormHotel).subscribe(data=>{
	        this.hotels = data;
	        console.log("pretrazeni hoteli");
	        console.log(data);
	    });
	}

}
