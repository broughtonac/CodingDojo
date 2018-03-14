import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {
  uri = "http://api.openweathermap.org/data/2.5/weather"
  cityPrefix = "?q="
  key = "&appid=61a3b5806dee0b48c980f295ce512a40"
  constructor(private _http: HttpClient) {}
  getWashington() {
    return this._http.get(this.uri + this.cityPrefix + "washington" + this.key)
  }
  getChicago() {
    return this._http.get(this.uri + this.cityPrefix + "chicago" + this.key)
  }
  getDallas() {
    return this._http.get(this.uri + this.cityPrefix + "dallas" + this.key)
  }
  getSeattle() {
    return this._http.get(this.uri + this.cityPrefix + "seattle" + this.key)
  }
}
