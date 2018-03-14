import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class BicycleService {
  constructor(private _http: HttpClient) {}
  getAll(callback) {
    return this._http.get("/bicycles")
    .subscribe(data => callback(data))
  }
  getOne(bid, callback) {
    return this._http.get("/bicycles/" + bid)
    .subscribe(data => callback(data))
  }
  getByUser(user, callback) {
    return this._http.get("/bicycles/user/" + user._id)
    .subscribe(data => callback(data))
  }
  create(newBicycle, user, callback) {
    return this._http.post("/bicycles/" + user._id, newBicycle)
    .subscribe(data => callback(data))
  }
  update(bid, oldBicycle, callback) {
    return this._http.put("/bicycles/" + bid, oldBicycle)
    .subscribe(data => callback(data))
  }
  destroy(bid, callback) {
    return this._http.delete("/bicycles/" + bid)
    .subscribe(data => callback(data))
  }
  search(query, callback) {
    return this._http.get("/bicycles/search/" + query)
    .subscribe(data => callback(data))
  }
}
