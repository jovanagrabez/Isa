import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ViewHotelsService } from '../services/view-hotels.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view-hotels',
  templateUrl: './view-hotels.component.html',
  styleUrls: ['./view-hotels.component.css', './animate.css']
})
export class ViewHotelsComponent implements OnInit {

  private hotelsArray: any;
  private selectedHotel: any;
  
  private loggedInUser: any;
  private isAdmin: any;
  
  private currentRate = 2.5;
  
  hotel$: Object;
  
  constructor(private router : Router, private viewHotelsService : ViewHotelsService) { }

  ngOnInit() {
  
  this.viewHotelsService.getHotels().subscribe(
  	data => this.hotel$ = data
  );
  
					  /*this.viewHotelsService.getHotels()
					  .subscribe(
					  	data=> 
					  	{
					  	this.hotelsArray = data;
					    console.log(this.hotelsArray);
					
					  	
					  	}
					  );
					  */
	
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

}
