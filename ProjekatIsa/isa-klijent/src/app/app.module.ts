import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient,} from '@angular/common/http';
import { HttpModule,Http } from '@angular/http';

import {RequestOptions, XHRBackend} from '@angular/http';
import { Router } from '@angular/router';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeGuestSearchComponent } from './home-guest-search/home-guest-search.component';
import { ViewAvioCompaniesComponent } from './view-avio-companies/view-avio-companies.component';
import { ViewHotelsComponent } from './view-hotels/view-hotels.component';
import { ViewRentalCarsComponent } from './view-rental-cars/view-rental-cars.component';
import { RegistrationComponent } from './registration/registration.component';

import { AviocompanySService } from './services/aviocompany-s.service';
import {ViewHotelsService} from './services/view-hotels.service';
import { ProfilcompanyComponent } from './profilcompany/profilcompany.component';
import { HotelDetailsComponent } from './hotel-details/hotel-details.component';


import { OrderModule } from 'ngx-order-pipe';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

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
    HotelDetailsComponent
  ],
  imports: [
   OrderModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpModule,
   NgbModule.forRoot()
  
  ],
  providers: [ HttpClientModule,AviocompanySService, ViewHotelsService
  /*{
       provide: Http,
      deps: [XHRBackend, RequestOptions, Router]
  
  }*/
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
