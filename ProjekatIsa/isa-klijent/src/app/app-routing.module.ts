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
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { AddCarsComponent } from './add-cars/add-cars.component';
import {FriendsComponent} from './profil/friends/friends.component';
import { AddFilijaleComponent } from './add-filijale/add-filijale.component';
import {FlightsComponent} from './aviocompany/flights/flights.component';
import {SeatComponent} from './aviocompany/flights/seat/seat.component';
import {FormsModule} from '@angular/forms';
import {FlightReservationComponent} from './aviocompany/flights/flight-reservation/flight-reservation.component';

import { FilCarsComponent } from './fil-cars/fil-cars.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { MyReservationsComponent } from './my-reservations/my-reservations.component';
import {SearchpageComponent} from './aviocompany/searchpage/searchpage.component';
import { HotelReportComponent } from './hotel-report/hotel-report.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { QuickReservationComponent } from './quick-reservation/quick-reservation.component';
import { ServiceReportComponent } from './service-report/service-report.component';
import { MakeDiscountsComponent } from './make-discounts/make-discounts.component';
import {AvioReportComponent} from './aviocompany/profilcompany/avio-report/avio-report.component';
import { QuickReservationHotelComponent } from './quick-reservation-hotel/quick-reservation-hotel.component';


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
    path: 'addHotel',
    component : AddHotelComponent
  },

  {
    path: 'addCar',
    component : AddCarsComponent
  },

  {
    path: 'addFilijale',
    component : AddFilijaleComponent
  },

  {
    path: 'profile',
    component : ProfilComponent
  },
  {
    path: 'friends',
    component : FriendsComponent
  },
  {
    path: 'fil-cars',
    component : FilCarsComponent
  },
  {
      path: 'addAdmin',
      component : AddAdminComponent
    },
  {
    path: 'flights/:id', component: FlightsComponent,
    children: [
      {
        path: 'seats', component: SeatComponent
      }
    ]
  },
  {
    path: 'flights/reservation/:id', component: FlightReservationComponent
  },
  {
    path: 'my-reservations', component:   MyReservationsComponent

  },
  {
      path: 'hotelReport', component:   HotelReportComponent


  },

  {
    path: 'search/flight', component:   SearchpageComponent

  },

  {
    path: 'change-password', component:   ChangePasswordComponent

  },

  {
    path: 'avioReport/:id', component:   AvioReportComponent

  },

  {
    path: 'quick-res', component:   QuickReservationComponent

  },

  {
    path: 'quick-res-hotel', component:   QuickReservationHotelComponent

  },


  {
    path: 'serviceReport', component:   ServiceReportComponent

  },

  {
    path: 'make-discounts', component:   MakeDiscountsComponent

  }

  










];

@NgModule({
  imports: [RouterModule.forRoot(routes), FormsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
