import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-game-two',
  templateUrl: './game-two.component.html',
  styleUrls: ['./game-two.component.css']
})
export class GameTwoComponent implements OnInit {
  private players: any
  constructor(private _httpService: HttpService, private _router: Router) {}
  ngOnInit() {
    let observable = this._httpService.getPlayers()
    observable.subscribe(data => {
      this.players = data["players"]
      console.log(this.players)
    })
  }
  getPlayers() {
    let observable = this._httpService.getPlayers()
    observable.subscribe(data => {
      this.players = data["players"]
    })
  }
  togglePlaying(id) {
    let observable = this._httpService.updatePlayer(id, {status: ["gameTwo", "playing"]})
    observable.subscribe(data => {
      this.getPlayers()
    })
  }
  toggleNotPlaying(id) {
    let observable = this._httpService.updatePlayer(id, {status: ["gameTwo", "notPlaying"]})
    observable.subscribe(data => {
      this.getPlayers()
    })
  }
  toggleUndecided(id) {
    let observable = this._httpService.updatePlayer(id, {status: ["gameTwo", "undecided"]})
    observable.subscribe(data => {
      this.getPlayers()
    })
  }
}