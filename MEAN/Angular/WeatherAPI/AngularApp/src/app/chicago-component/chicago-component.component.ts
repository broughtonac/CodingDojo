import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-chicago-component',
  templateUrl: './chicago-component.component.html',
  styleUrls: ['./chicago-component.component.css']
})
export class ChicagoComponentComponent implements OnInit {
  weather: any
  constructor(private _httpService: HttpService, private _router: Router) { }
  ngOnInit() {
    this.weather = {}
    this.getForecast()
  }
  getForecast() {
    this._httpService.getChicago().subscribe(data => {
      console.log(data)
      this.weather = {
        humidity: data["main"]["humidity"],
        average: data["main"]["temp"],
        high: data["main"]["temp_max"],
        low: data["main"]["temp_min"],
        status: data["weather"][0]["description"]
      }
    })
  }
}
