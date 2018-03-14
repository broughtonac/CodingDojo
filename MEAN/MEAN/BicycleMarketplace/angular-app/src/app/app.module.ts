import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EntryComponent } from './entry/entry.component';
import { UserService } from './user.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LandingComponent } from './landing/landing.component';
import { RandomComponent } from './random/random.component';
import { ListingsComponent } from './listings/listings.component';
import { BrowseComponent } from './browse/browse.component';
import { BicycleService } from './bicycle.service';

@NgModule({
  declarations: [
    AppComponent,
    EntryComponent,
    LandingComponent,
    RandomComponent,
    ListingsComponent,
    BrowseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    UserService,
    BicycleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
