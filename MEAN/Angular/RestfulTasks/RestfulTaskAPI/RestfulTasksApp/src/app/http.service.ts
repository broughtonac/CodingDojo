import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {

  constructor(private _http: HttpClient) {
    console.log("service constructed")
    this.getTasks();
  }

  getTasks() {
    console.log("getting tasks")
    let tempObservable = this._http.get('/tasks');
    tempObservable.subscribe(data => console.log("got tasks", data));
  }

  getTaskById(id) {
    let tempObservable = this._http.get('/tasks/' + id)
    tempObservable.subscribe(data => console.log("got task", data))
  }
}
