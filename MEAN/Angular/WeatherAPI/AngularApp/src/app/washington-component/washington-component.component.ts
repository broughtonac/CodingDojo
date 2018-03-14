import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'

@Component({
  selector: 'app-washington-component',
  templateUrl: './washington-component.component.html',
  styleUrls: ['./washington-component.component.css']
})
export class WashingtonComponentComponent implements OnInit {
  weather: any
  constructor(private _httpService: HttpService) { }
  ngOnInit() {
    this.weather = {}
    this.getForecast()
  }
  getForecast() {
    this._httpService.getWashington().subscribe(data => {
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
