import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListComponent } from './list/list.component';
import { AddPlayerComponent } from './add-player/add-player.component';
import { GameOneComponent } from './game-one/game-one.component';
import { GameTwoComponent } from './game-two/game-two.component';
import { GameThreeComponent } from './game-three/game-three.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  {path: "players/list", component: ListComponent},
  {path: "players/addplayer", component: AddPlayerComponent},
  {path: "status/game/1", component: GameOneComponent},
  {path: "status/game/2", component: GameTwoComponent},
  {path: "status/game/3", component: GameThreeComponent},
  {path: "", pathMatch: "full", redirectTo: "/"},
  {path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
