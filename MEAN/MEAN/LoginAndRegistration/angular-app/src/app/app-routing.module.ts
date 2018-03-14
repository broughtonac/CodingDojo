import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {path: "register", component: UserComponent},
  {path: "login", component: UserComponent},
  {path: "dashboard", component: DashboardComponent},
  {path: "", pathMatch: "full", redirectTo: "/register"}, // set landing route to `/register`
  {path: "**", redirectTo: "/register"} // set catch route to `/register`
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
