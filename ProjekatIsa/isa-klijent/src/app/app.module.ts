import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient,} from '@angular/common/http';
import { HttpModule,Http } from '@angular/http';
import { DatePipe } from '@angular/common';

import {RequestOptions, XHRBackend} from '@angular/http';
import {Router, RouterModule} from '@angular/router';
import { FormsModule } from '@angular/forms';




import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeGuestSearchComponent } from './home-guest-search/home-guest-search.component';
import { ViewAvioCompaniesComponent } from './aviocompany/view-avio-companies/view-avio-companies.component';
import { ViewHotelsComponent } from './view-hotels/view-hotels.component';
import { ViewRentalCarsComponent } from './view-rental-cars/view-rental-cars.component';
import { RegistrationComponent } from './registration/registration.component';

import { AviocompanySService } from './services/aviocompany-s.service';
import {ViewHotelsService} from './services/view-hotels.service';
import { ProfilcompanyComponent } from './aviocompany/profilcompany/profilcompany.component';
import { HotelDetailsComponent } from './hotel-details/hotel-details.component';
import { AuthServiceService } from './services/auth-service.service';
import { TokensService } from './auth/tokens/tokens.service';




import { OrderModule } from 'ngx-order-pipe';
 import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { RentacarDetailsComponent } from './rentacar-details/rentacar-details.component';
import { AddAviocompanyComponent } from './aviocompany/add-aviocompany/add-aviocompany.component';
import { ConfirmRegistrationComponent } from './confirm-registration/confirm-registration.component';
import { DestinationComponent } from './aviocompany/destination/destination.component';
import { ProfilComponent } from './profil/profil.component';
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { AddCarsComponent } from './add-cars/add-cars.component';
import { AddFilijaleComponent } from './add-filijale/add-filijale.component';
 import { MatCheckboxModule, MatButtonModule} from '@angular/material';
 import {MatStepperModule} from '@angular/material/stepper';
import { FriendsComponent } from './profil/friends/friends.component';
import { FlightsComponent } from './aviocompany/flights/flights.component';
import { SeatComponent } from './aviocompany/flights/seat/seat.component';
 import {MatIconModule} from '@angular/material/icon';
import { FlightReservationComponent } from './aviocompany/flights/flight-reservation/flight-reservation.component';
import { FilCarsComponent } from './fil-cars/fil-cars.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { MyReservationsComponent } from './my-reservations/my-reservations.component';
import { HotelReportComponent } from './hotel-report/hotel-report.component';

import { NgSelectModule } from '@ng-select/ng-select';
import { SearchpageComponent } from './aviocompany/searchpage/searchpage.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeGuestSearchComponent,
    ViewAvioCompaniesComponent,
    ViewHotelsComponent,
    ViewRentalCarsComponent,
    RegistrationComponent,
    ProfilcompanyComponent,
    HotelDetailsComponent,
    RentacarDetailsComponent,
    AddAviocompanyComponent,
    ConfirmRegistrationComponent,
    DestinationComponent,
    ProfilComponent,


    AddHotelComponent,
    AddCarsComponent,
    AddFilijaleComponent,
    FriendsComponent,
    FlightsComponent,
    SeatComponent,
    FlightReservationComponent,
    FilCarsComponent,
    AddAdminComponent,
    MyReservationsComponent,
    HotelReportComponent,
    MyReservationsComponent,
    SearchpageComponent




  ],
  imports: [
   OrderModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatCheckboxModule,
    MatStepperModule,
    MatButtonModule,
    MatIconModule,
    HttpModule,
   NgbModule.forRoot(),
   FormsModule,
    BrowserAnimationsModule,
    NgSelectModule




  ],
  providers: [ HttpClientModule,DatePipe, AviocompanySService, ViewHotelsService,
    AuthServiceService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
