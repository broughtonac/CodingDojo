import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-game-one',
  templateUrl: './game-one.component.html',
  styleUrls: ['./game-one.component.css']
})
export class GameOneComponent implements OnInit {
  private players: any
  constructor(private _httpService: HttpService, private _router: Router) {}
  ngOnInit() {
    let observable = this._httpService.getPlayers()
    observable.subscribe(data => {
      this.players = data["players"]
    })
  }
  getPlayers() {
    let observable = this._httpService.getPlayers()
    observable.subscribe(data => {
      this.players = data["players"]
    })
  }
  togglePlaying(id) {
    let gameOne = ""
    this._httpService.getPlayer(id).subscribe(data => {
      let player = data["player"]
      gameOne = player["gameOne"]
      if (gameOne == "playing") {
        gameOne = ""
      }
      else {
        gameOne = "playing"
      }
      this._httpService.updatePlayer(
        id,
        {status: ["gameOne", gameOne]}
      ).subscribe(data => {
        this.getPlayers()
      })
    })
  }
  toggleNotPlaying(id) {
    let gameOne = ""
    this._httpService.getPlayer(id).subscribe(data => {
      let player = data["player"]
      gameOne = player["gameOne"]
      if (gameOne == "notPlaying") {
        gameOne = ""
      }
      else {
        gameOne = "notPlaying"
      }
      this._httpService.updatePlayer(id, {status: ["gameOne", gameOne]}).subscribe(data => {
        this.getPlayers()
      })
    })
  }
  toggleUndecided(id) {
    let gameOne = ""
    this._httpService.getPlayer(id).subscribe(data => {
      let player = data["player"]
      gameOne = player["gameOne"]
      if (gameOne == "undecided") {
        gameOne = ""
      }
      else {
        gameOne = "undecided"
      }
      this._httpService.updatePlayer(id, {status: ["gameOne", gameOne]}).subscribe(data => {
        this.getPlayers()
      })
    })
  }
}