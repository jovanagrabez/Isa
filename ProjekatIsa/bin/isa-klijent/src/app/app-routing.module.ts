import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeGuestSearchComponent } from './home-guest-search/home-guest-search.component';
import { ViewAvioCompaniesComponent } from './view-avio-companies/view-avio-companies.component';
import { ViewHotelsComponent } from './view-hotels/view-hotels.component';
import { ViewRentalCarsComponent } from './view-rental-cars/view-rental-cars.component';
import { RegistrationComponent } from './registration/registration.component';
import { HotelDetailsComponent } from './hotel-details/hotel-details.component';
import {ProfilcompanyComponent } from './profilcompany/profilcompany.component';
import { RentacarDetailsComponent } from './rentacar-details/rentacar-details.component';


const routes: Routes = [
	
	{
	  path: '',
	  redirectTo: '/home',
	  pathMatch: 'full'
	},
	{
	  path: 'home',
	  component: HomeGuestSearchComponent
	},
	{
	  path: 'login',
	  component: LoginComponent
	},
	{
	  path: 'avioCompany',
	  component: ViewAvioCompaniesComponent
	},
	{
	  path: 'hotels',
	  component: ViewHotelsComponent
	},
	{
	  path: 'rentalCars',
	  component: ViewRentalCarsComponent
	},
    {
        path: 'registracija',
        component: RegistrationComponent
    },
    {
    	path: 'hotel-details',
    	component: HotelDetailsComponent
    	
    },
     {
        path: 'profilcompany',
        component: ProfilcompanyComponent
        
    },
     {
         path: 'rentacar-details',
         component : RentacarDetailsComponent
     }
    
	
	
	
	
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
