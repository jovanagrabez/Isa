import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeGuestSearchComponent } from './home-guest-search/home-guest-search.component';
import { ViewAvioCompaniesComponent } from './aviocompany/view-avio-companies/view-avio-companies.component';
import { ViewHotelsComponent } from './view-hotels/view-hotels.component';
import { ViewRentalCarsComponent } from './view-rental-cars/view-rental-cars.component';
import { RegistrationComponent } from './registration/registration.component';
import { HotelDetailsComponent } from './hotel-details/hotel-details.component';
import {ProfilcompanyComponent } from './aviocompany/profilcompany/profilcompany.component';
import { RentacarDetailsComponent } from './rentacar-details/rentacar-details.component';
import {AddAviocompanyComponent} from './aviocompany/add-aviocompany/add-aviocompany.component';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component'
import {DestinationComponent} from './aviocompany/destination/destination.component';
import {ProfilComponent} from './profil/profil.component';


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
        path: 'profilcompany/:id',
        component: ProfilcompanyComponent,
       children: [
         {
           path: 'addDestination', component: DestinationComponent
         }
       ]

        
    },
     {
         path: 'rentacar-details',
         component : RentacarDetailsComponent
     },
     {
         path: 'confirm-registration/:id',
         component : ConfirmRegistrationComponent
     },

  {
    path: 'addAviocompany',
    component : AddAviocompanyComponent
  },

  {
    path: 'destination',
    component : DestinationComponent
  },

  {
    path: 'profile',
    component : ProfilComponent
  }








];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
