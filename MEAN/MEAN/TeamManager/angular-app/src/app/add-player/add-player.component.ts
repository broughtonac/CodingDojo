import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-add-player',
  templateUrl: './add-player.component.html',
  styleUrls: ['./add-player.component.css']
})
export class AddPlayerComponent implements OnInit {
  private player: any
  constructor(private _httpService: HttpService, private _router: Router) {}
  ngOnInit() {
    this.player = {name: "", position: "", gameOne: "", gameTwo: "undecided", gameThree: ""}
  }
  createPlayer() {
    let observable = this._httpService.createPlayer(this.player)
    observable.subscribe(data => {
      this._router.navigate(["/players/list"])
    })
  }
}
