import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable()
export class HttpService {
  constructor(private _http: HttpClient) {
    this.getBooks()
  }
  getBooks() {
    let obs = this._http.get("/books")
    obs.subscribe(data => console.log(data))
  }
}
