import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
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
  deletePlayer(id) {
    if (confirm("Are you sure?")) {
      let observable = this._httpService.deletePlayer(id)
      observable.subscribe(data => {
          this.getPlayers()
      })
    }
  }
}
