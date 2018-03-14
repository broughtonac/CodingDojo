import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  gold = 0
  log = []
  constructor(private _httpService: HttpService) {}
  ngOnInit() {}
  farm() {
    let amount = Math.floor(Math.random() * (5 - 2 + 1)) + 2
    this.gold += amount
    this.log.push("earned " + amount + " at farm")
  }
  cave() {
    let amount = Math.floor(Math.random() * (5 - 10 + 1)) + 10
    this.gold += amount
    this.log.push("earned " + amount + " at cave")
  }
  house() {
    let amount = Math.floor(Math.random() * (7 - 15 + 1)) + 15
    this.gold += amount
    this.log.push("earned " + amount + " at house")
  }
  casino() {
    let amount = Math.floor(Math.random() * 100)
    if (Math.random() < 0.5) {
      this.gold -= amount
      this.log.push("lost " + amount + " at casino")
    }
    else {
      this.gold += amount
      this.log.push("earned " + amount + " at casino")
    }
  }
}
