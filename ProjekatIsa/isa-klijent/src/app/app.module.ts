import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginLogoutComponent } from './login-logout/login-logout.component';
import { HomeGuestComponent } from './home-guest/home-guest.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginLogoutComponent,
    HomeGuestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
