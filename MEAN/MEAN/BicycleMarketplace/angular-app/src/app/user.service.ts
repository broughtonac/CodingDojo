import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserService {
  constructor(private _http: HttpClient) {}
  register(user, callback) {
    return this._http.post("/register", user)
    .subscribe(data => callback(data))
  }
  login(user, callback) {
    return this._http.post("/login", user)
    .subscribe(data => callback(data))
  }
  session(callback) {
    this._http.get("/session")
    .subscribe(data => callback(data))
  }
  logout() {
    this._http.get("/logout")
    .subscribe()
  }
  getOne(uid, callback) {
    this._http.get("/users/" + uid)
    .subscribe(data => callback(data))
  }
}
