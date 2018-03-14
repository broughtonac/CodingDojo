import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SeattleComponentComponent } from './seattle-component/seattle-component.component';
import { DallasComponentComponent } from './dallas-component/dallas-component.component';
import { WashingtonComponentComponent } from './washington-component/washington-component.component';
import { ChicagoComponentComponent } from './chicago-component/chicago-component.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: "washington", component: WashingtonComponentComponent},
  { path: "chicago", component: ChicagoComponentComponent},
  { path: "dallas", component: DallasComponentComponent},
  { path: "seattle", component: SeattleComponentComponent},
  { path: "", pathMatch: "full", redirectTo: "/"},
  { path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
