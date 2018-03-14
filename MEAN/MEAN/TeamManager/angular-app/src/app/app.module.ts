import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ManagePlayersComponent } from './manage-players/manage-players.component';
import { ManagePlayerStatusComponent } from './manage-player-status/manage-player-status.component';
import { HttpService } from './http.service'
import { HttpClientModule } from '@angular/common/http';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ListComponent } from './list/list.component';
import { AddPlayerComponent } from './add-player/add-player.component';
import { GameOneComponent } from './game-one/game-one.component';
import { GameTwoComponent } from './game-two/game-two.component';
import { GameThreeComponent } from './game-three/game-three.component'
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ManagePlayersComponent,
    ManagePlayerStatusComponent,
    PageNotFoundComponent,
    ListComponent,
    AddPlayerComponent,
    GameOneComponent,
    GameTwoComponent,
    GameThreeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
