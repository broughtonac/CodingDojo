import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EntryComponent } from './entry/entry.component';
import { LandingComponent } from './landing/landing.component';
import { RandomComponent } from './random/random.component';
import { BrowseComponent } from './browse/browse.component';
import { ListingsComponent } from './listings/listings.component';

const routes: Routes = [
  {path: "", component: LandingComponent}, // contains Random, Entry
  {path: "entry", component: EntryComponent},
  {path: "random", component: RandomComponent},
  {path: "browse", component: BrowseComponent},
  {path: "listings", component: ListingsComponent}, // contains Create, Modify
  {path: "", pathMatch: "full", redirectTo: "/"}, // set landing route (it's set to default here)
  {path: "**", redirectTo: "/"} // set catch route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
